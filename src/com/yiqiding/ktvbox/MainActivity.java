package com.yiqiding.ktvbox;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.http.util.EncodingUtils;

import com.yiqiding.ktvbox.globalcontrol.ApplicationExtend;
import com.yiqiding.ktvbox.imagedownload.ImageListener;
import com.yiqiding.ktvbox.infomanage.AlbumInfoStructure;
import com.yiqiding.ktvbox.infomanage.InfoFunctionInterface;
import com.yiqiding.ktvbox.infomanage.InfoXmlIO;
import com.yiqiding.ktvbox.infomanage.MusicInfoStructure;
import com.yiqiding.ktvbox.operatemanage.ImageDownloadOperateManage;
import com.yiqiding.ktvbox.operatemanage.VideoDownloadOperateManage;
import com.yiqiding.ktvbox.videodownload.VideoDownloadTask;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class MainActivity extends Activity implements ImageListener {

	/**
	 * Used for DDMS logging
	 */
	private static final String TAG = "MainActivity";

	/** column number **/
	private static final int COLUMNS = 2;
	/** imageView default height **/
	private static final int IMAGEVIEW_DEFAULT_HEIGHT = 200;

	Context context = ApplicationExtend.getInstance().getApplicationContext();

	private RelativeLayout parentLayout;

	private ArrayList<String> imageUrlList = null;

	private void initImageUrlList() {
		imageUrlList = new ArrayList<String>();
		imageUrlList
				.add("http://farm8.staticflickr.com/7403/9146300103_03423db0cc.jpg");
		imageUrlList
				.add("http://farm4.staticflickr.com/3755/9148527824_6c156185ea.jpg");
		imageUrlList
				.add("http://farm8.staticflickr.com/7409/9148527822_36fa37d7ca_z.jpg");
		imageUrlList
				.add("http://farm8.staticflickr.com/7403/9146300103_03423db0cc.jpg");
		imageUrlList
				.add("http://farm8.staticflickr.com/7318/9148527808_e804baef0b.jpg");
		imageUrlList
				.add("http://farm3.staticflickr.com/2857/9148527928_3063544889.jpg");
		imageUrlList
				.add("http://farm8.staticflickr.com/7318/9146300275_5fe995d123.jpg");
		imageUrlList
				.add("http://farm8.staticflickr.com/7351/9148527976_8a4e75ae87.jpg");
		imageUrlList
				.add("http://farm4.staticflickr.com/3679/9146300263_5c2191232a_o.jpg");
		imageUrlList
				.add("http://farm3.staticflickr.com/2863/9148527892_31f9377351_o.jpg");
		imageUrlList
				.add("http://farm3.staticflickr.com/2888/9148527996_f05118d7de_o.jpg");
		imageUrlList
				.add("http://farm8.staticflickr.com/7310/9148528008_8e8f51997a.jpg");
		imageUrlList
				.add("http://farm3.staticflickr.com/2849/9148528108_dfcda19507.jpg");
		imageUrlList
				.add("http://farm4.staticflickr.com/3739/9148528022_e9bf03058f.jpg");
		imageUrlList
				.add("http://farm4.staticflickr.com/3696/9146300409_dfa9d7c603.jpg");
		imageUrlList
				.add("http://farm8.staticflickr.com/7288/9146300469_bd3420c75b_z.jpg");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ApplicationExtend.getInstance().registerImageListener(this);

		Button downloadVideoButton = (Button) findViewById(R.id.download_video);
		downloadVideoButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				VideoDownloadOperateManage.requestVideo("http://192.168.1.72/VideoFiles/shaonvshidai-720P.MP4", "0",
						null);

			}
		});

		parentLayout = (RelativeLayout) findViewById(R.id.ViewContainer);

		initImageUrlList();

		int count = 0, viewId = 0x7F24FFF0;
		int verticalSpacing, horizontalSpacing;
		verticalSpacing = horizontalSpacing = getResources()
				.getDimensionPixelSize(R.dimen.dp_4);
		Display display = getWindowManager().getDefaultDisplay();
		int imageWidth = (display.getWidth() - (COLUMNS + 1)
				* horizontalSpacing)
				/ COLUMNS;
		for (String imageUrl : imageUrlList) {
			ImageView imageView = new ImageView(context);
			imageView.setId(++viewId);
			imageView.setScaleType(ScaleType.CENTER);
			imageView.setBackgroundResource(R.drawable.image_border);
			parentLayout.addView(imageView);

			// set imageView layout params
			LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView
					.getLayoutParams();
			layoutParams.width = imageWidth;
			layoutParams.topMargin = verticalSpacing;
			layoutParams.rightMargin = horizontalSpacing;
			int column = count % COLUMNS;
			int row = count / COLUMNS;
			if (row > 0) {
				layoutParams.addRule(RelativeLayout.BELOW, viewId - COLUMNS);
			}
			if (column > 0) {
				layoutParams.addRule(RelativeLayout.RIGHT_OF, viewId - 1);
			}

			imageView.setTag(imageUrl);
			imageView.setImageDrawable(null);
			layoutParams.height = IMAGEVIEW_DEFAULT_HEIGHT;

			ImageDownloadOperateManage.requestImage(imageUrl, imageUrlList,
					true);

			count++;
		}

		//test xml
		this.testXmlParse();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onHandleImage(String imageId, Bitmap bitmap) {

		for (int i = 0; i < parentLayout.getChildCount(); i++) {
			ImageView imageView = (ImageView) parentLayout.getChildAt(i);
			String url = (String) imageView.getTag();
			if (url.contains(imageId)) {
				imageView.setImageBitmap(bitmap);
			}
		}
	}
	

	private Bundle resultBundle;
	
	private void testXmlParse()
	{
		try {
			InfoXmlIO infoXmlIO = new InfoXmlIO();
			
			InputStream in = ApplicationExtend.getInstance().getAssets().open("10003.xml");
			int length = in.available();

			byte[] buffer = new byte[length];  
			in.read(buffer);  

			String xmlData = EncodingUtils.getString(buffer, "UTF-8");

			resultBundle = infoXmlIO.parseInfoXml(InfoFunctionInterface.functionnum_music_list, xmlData);
			Log.v(TAG, String.valueOf(length));
			Log.v(TAG, String.valueOf(resultBundle.getInt(InfoFunctionInterface.total)));
			Log.v(TAG, String.valueOf(resultBundle.getInt(InfoFunctionInterface.page)));
			ArrayList<AlbumInfoStructure> albums = resultBundle.getParcelableArrayList(InfoFunctionInterface.albums);
			
			Iterator<AlbumInfoStructure> it = albums.iterator();
			
			while(it.hasNext())
			{
				AlbumInfoStructure albumInfoStructure = it.next();
				Log.v(TAG, "//////////////////////////////////////");
				Log.v(TAG, String.valueOf(albumInfoStructure.getAlbumId()));
				Log.v(TAG, albumInfoStructure.getName());
				Log.v(TAG, albumInfoStructure.getRelease());
				Log.v(TAG, albumInfoStructure.getDetail());
				Log.v(TAG, albumInfoStructure.getImage());
				
				ArrayList<MusicInfoStructure> musics = (ArrayList<MusicInfoStructure>)albumInfoStructure.getMusics();
				
				Iterator<MusicInfoStructure> it_m = musics.iterator();
				while (it_m.hasNext()) {
					MusicInfoStructure musicInfoStructure = (MusicInfoStructure) it_m.next();
					Log.v(TAG, String.valueOf(musicInfoStructure.getMusicId()));
					Log.v(TAG, musicInfoStructure.getName());
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
