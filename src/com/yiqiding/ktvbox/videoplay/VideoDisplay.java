package com.yiqiding.ktvbox.videoplay;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TimerTask;

import com.yiqiding.ktvbox.R;
import com.yiqiding.ktvbox.globalcontrol.ApplicationExtend;
import com.yiqiding.ktvbox.globalcontrol.MainThreadMessageID;
import com.yiqiding.ktvbox.operatemanage.VideoDownloadOperateManage;
import com.yiqiding.ktvbox.videodownload.VideoFileListener;

import android.app.Presentation;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoDisplay extends Presentation implements VideoFileListener,
		MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, OnErrorListener {

	public static final int DOWNLOADMODE = 0;
	public static final int REMOTESTREAMMODE = 1;
	public static final int LOCALMODE = 2;
	private int mode = DOWNLOADMODE;

	private VideoView videoView;
	private FrameLayout viewContainer;
	private MediaPlayer mp = null;
	private String currentVideoId;
	private MediaPlayer.TrackInfo info[];
	private int trackPos = 0;

	private AudioManager audioMgr = null;
	private int maxVolume = 0;
	private int curVolume = 0;

	private String remoteUrl;
	private String videoPath;

	private ArrayList<DynamicViewParamStructure> dynamicViewParamStructures = new ArrayList<DynamicViewParamStructure>();

	public VideoDisplay(Context outerContext, Display display) {
		super(outerContext, display);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Inflate the layout.
		setContentView(R.layout.videodisplay);

		videoView = (VideoView) findViewById(R.id.VideoView);
		viewContainer = (FrameLayout) findViewById(R.id.ViewContainer);

		ApplicationExtend.getInstance().registerVideoFileListener(this);

		videoView.setMediaController(new MediaController(ApplicationExtend
				.getInstance().getApplicationContext()));
		videoView.setOnPreparedListener(this);
		videoView.setOnCompletionListener(this);
		videoView.setOnErrorListener(this);

		audioMgr = (AudioManager) ApplicationExtend.getInstance()
				.getSystemService(Context.AUDIO_SERVICE);
		maxVolume = audioMgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		curVolume = maxVolume / 2;
		audioMgr.setStreamVolume(AudioManager.STREAM_MUSIC, curVolume,
				AudioManager.FLAG_PLAY_SOUND);

	}

	@Override
	public void onHandleVideo(String videoId, String videoPath, int mode) {

		trackPos = 0;
		
		this.mode = mode;

		if (mode == REMOTESTREAMMODE) {
			this.remoteUrl = videoPath;
			this.videoPath = null;
			
			this.playRemoteVideo();
		}

		if (mode == DOWNLOADMODE) {
			currentVideoId = videoId;
			videoView.setVideoPath(videoPath);
			videoView.start();
		}
		
		if (mode == LOCALMODE) {
			videoView.setVideoPath(videoPath);
			videoView.start();
		}
	}

	@Override
	public void onHandleNoVideo() {

	}

	@Override
	public void onPrepared(MediaPlayer mp) {

		this.mp = mp;
		info = mp.getTrackInfo();
		if (info.length >= 1) {
			mp.selectTrack(trackPos);
		}

		if (mode == REMOTESTREAMMODE) {
			videoView.seekTo(curPosition);
		}
		
		mp.start();
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		if (mode == DOWNLOADMODE) {
			videoView.pause();
			
			VideoDownloadOperateManage.finishPlayOneVideo(currentVideoId);
			VideoDownloadOperateManage.requestNextPlayVideo();
		}
		
		if (mode==REMOTESTREAMMODE) {
			curPosition = 0;
			videoView.pause();
			
			VideoDownloadOperateManage.requestNextPlayVideo();
		}
		
		if (mode == LOCALMODE) {
			videoView.pause();
		}

		
		videoView.stopPlayback();
	}
	
	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		if (mode == REMOTESTREAMMODE)
		{
			iserror = true;
			errorCnt++;
			videoView.pause();
			return true;
		}else {
			this.onCompletion(mp);
			return true;
		}

	}

	public void start() {
		videoView.start();
	}

	public void pause() {
		if (videoView.canPause()) {
			videoView.pause();
		}
	}

	public void stop() {
		if (mode == DOWNLOADMODE) {
			videoView.pause();
			VideoDownloadOperateManage.finishPlayOneVideo(currentVideoId);
		}
		
		if (mode==REMOTESTREAMMODE) {
			curPosition = 0;
			videoView.pause();
		}
		
		if (mode == LOCALMODE) {
			videoView.pause();
		}

		videoView.stopPlayback();
	}

	public void seekTo(float percentage) {
		int seekToPos = (int) (percentage * videoView.getDuration());
		if (seekToPos > videoView.getCurrentPosition()) {
			if (videoView.canSeekForward()) {
				videoView.seekTo(seekToPos);
			}
		}

		if (seekToPos < videoView.getCurrentPosition()) {
			if (videoView.canSeekBackward()) {
				videoView.seekTo(seekToPos);
			}
		}
	}

	public void next() {
		if (mode == DOWNLOADMODE) {
			videoView.pause();
			VideoDownloadOperateManage.finishPlayOneVideo(currentVideoId);
			VideoDownloadOperateManage.requestNextPlayVideo();
		}
		
		if (mode==REMOTESTREAMMODE) {
			curPosition = 0;
			videoView.pause();
			
			VideoDownloadOperateManage.requestNextPlayVideo();
		}
		
		if (mode == LOCALMODE) {
			videoView.pause();
		}

		videoView.stopPlayback();
	}

	public void switchTrack() {
		if (info.length >= 1) {
			trackPos++;
			if (trackPos >= info.length) {
				trackPos = 0;
			}
			if (mp != null) {
				mp.selectTrack(trackPos);
			}
		}
	}

	public void adjustVolume(float volume) {
		curVolume = (int) (maxVolume * volume);
		audioMgr.setStreamVolume(AudioManager.STREAM_MUSIC, curVolume,
				AudioManager.FLAG_PLAY_SOUND);
	}

	public void enableMute(Boolean isMute) {
		if (isMute) {
			audioMgr.setStreamMute(AudioManager.STREAM_MUSIC, true);
		} else {
			audioMgr.setStreamMute(AudioManager.STREAM_MUSIC, false);

			audioMgr.setStreamVolume(AudioManager.STREAM_MUSIC, curVolume,
					AudioManager.FLAG_PLAY_SOUND);
		}
	}

	public void addView(DynamicViewParamStructure dynamicViewParamStructure) {
		dynamicViewParamStructures.add(dynamicViewParamStructure);

		viewContainer.addView(dynamicViewParamStructure.getView(),
				dynamicViewParamStructure.getLayoutParams());
		dynamicViewParamStructure.getView().startAnimation(
				dynamicViewParamStructure.getInAnimation());

		dynamicViewParamStructure.getTimer().schedule(
				new DynamicViewTimerTask(dynamicViewParamStructure.getId()), 0,
				dynamicViewParamStructure.getDuration());
	}

	public void removeView(int viewId) {
		Iterator<DynamicViewParamStructure> it = dynamicViewParamStructures
				.iterator();

		while (it.hasNext()) {
			DynamicViewParamStructure dynamicViewParamStructure = (DynamicViewParamStructure) it
					.next();
			if (dynamicViewParamStructure.getId() == viewId) {
				dynamicViewParamStructure.getOutAnimation()
						.setAnimationListener(
								new RemoveAnimationListener(
										dynamicViewParamStructure));
				dynamicViewParamStructure.getView().startAnimation(
						dynamicViewParamStructure.getOutAnimation());
				break;
			}
		}
	}

	private class DynamicViewTimerTask extends TimerTask {

		private int taskId;

		public DynamicViewTimerTask(int taskId) {
			this.taskId = taskId;
		}

		@Override
		public void run() {
			// send message to main thread (for remove view)
			Message msg = new Message();
			msg.what = MainThreadMessageID.MESSAGE_VIDEO_REMOVE_DYNAMICVIEW;
			msg.arg1 = this.taskId;
			ApplicationExtend.getInstance().getMainThreadhandler()
					.sendMessage(msg);

			this.cancel();
		}
	}

	private class RemoveAnimationListener implements AnimationListener {
		private DynamicViewParamStructure removeDynamicViewParamStructure;

		public RemoveAnimationListener(
				DynamicViewParamStructure removeDynamicViewParamStructure) {
			this.removeDynamicViewParamStructure = removeDynamicViewParamStructure;
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			viewContainer.removeView(removeDynamicViewParamStructure.getView());
			dynamicViewParamStructures.remove(removeDynamicViewParamStructure);
			removeDynamicViewParamStructure = null;
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
		}

		@Override
		public void onAnimationStart(Animation animation) {
		}

	}
	
	private static final int READY_BUFF = 2000 * 1024;
	private static final int CACHE_BUFF = 500 * 1024;

	private boolean isready = false;
	private boolean iserror = false;
	private int errorCnt = 0;
	private int curPosition = 0;
	private long mediaLength = 0;
	private long readSize = 0;

	private void playRemoteVideo() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				FileOutputStream out = null;
				InputStream is = null;

				try {
					URL url = new URL(remoteUrl);
					HttpURLConnection httpConnection = (HttpURLConnection) url
							.openConnection();

					if (videoPath == null) {
						videoPath = Environment.getExternalStorageDirectory()
								.getAbsolutePath()
								+ "/VideoCache/"
								+ System.currentTimeMillis() + ".mp4";
					}

					File cacheFile = new File(videoPath);

					if (!cacheFile.exists()) {
						cacheFile.getParentFile().mkdirs();
						cacheFile.createNewFile();
					}

					readSize = cacheFile.length();
					out = new FileOutputStream(cacheFile, true);

					httpConnection.setRequestProperty("User-Agent", "NetFox");
					httpConnection.setRequestProperty("RANGE", "bytes="
							+ readSize + "-");

					is = httpConnection.getInputStream();

					mediaLength = httpConnection.getContentLength();
					if (mediaLength == -1) {
						return;
					}

					mediaLength += readSize;

					byte buf[] = new byte[4 * 1024];
					int size = 0;
					long lastReadSize = 0;

					mHandler.sendEmptyMessage(VIDEO_STATE_UPDATE);
					
					while ((size = is.read(buf)) != -1) {
						try {
							out.write(buf, 0, size);
							readSize += size;
						} catch (Exception e) {
							e.printStackTrace();
						}

						if (!isready) {
							if ((readSize - lastReadSize) > READY_BUFF) {
								lastReadSize = readSize;
								mHandler.sendEmptyMessage(CACHE_VIDEO_READY);
							}
						} else {
							if ((readSize - lastReadSize) > CACHE_BUFF
									* (errorCnt + 1)) {
								lastReadSize = readSize;
								mHandler.sendEmptyMessage(CACHE_VIDEO_UPDATE);
							}
						}
					}

					mHandler.sendEmptyMessage(CACHE_VIDEO_END);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (out != null) {
						try {
							out.close();
						} catch (IOException e) {
							//
						}
					}

					if (is != null) {
						try {
							is.close();
						} catch (IOException e) {
							//
						}
					}
				}

			}
		}).start();

	}

	private final static int VIDEO_STATE_UPDATE = 0;
	private final static int CACHE_VIDEO_READY = 1;
	private final static int CACHE_VIDEO_UPDATE = 2;
	private final static int CACHE_VIDEO_END = 3;

	private final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case VIDEO_STATE_UPDATE:
				double cachepercent = readSize * 100.00 / mediaLength * 1.0;
				String s = String.format("已缓存: [%.2f%%]", cachepercent);
				if (videoView.isPlaying()) {
					curPosition = videoView.getCurrentPosition();
					int duration = videoView.getDuration();
					duration = duration == 0 ? 1 : duration;

					double playpercent = curPosition * 100.00 / duration * 1.0;

					int i = curPosition / 1000;
					int hour = i / (60 * 60);
					int minute = i / 60 % 60;
					int second = i % 60;

					s += String.format(" 播放: %02d:%02d:%02d [%.2f%%]", hour,
							minute, second, playpercent);

					if (curPosition<duration) {
						mHandler.sendEmptyMessageDelayed(VIDEO_STATE_UPDATE, 1000);
					}
				}
				break;
				
			case CACHE_VIDEO_READY:
				isready = true;
				videoView.setVideoPath(videoPath);
				videoView.start();
				break;

			case CACHE_VIDEO_UPDATE:
				if (iserror) {
					videoView.setVideoPath(videoPath);
					videoView.start();
					iserror = false;
				}
				break;

			case CACHE_VIDEO_END:
				if (iserror) {
					videoView.setVideoPath(videoPath);
					videoView.start();
					iserror = false;
				}
				break;
			}

			super.handleMessage(msg);
		}
	};


}
