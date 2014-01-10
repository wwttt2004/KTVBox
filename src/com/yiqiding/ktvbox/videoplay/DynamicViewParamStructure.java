package com.yiqiding.ktvbox.videoplay;

import java.util.Timer;

import android.app.ActionBar.LayoutParams;
import android.view.View;
import android.view.animation.Animation;

public class DynamicViewParamStructure {
	private int id;
	private View view;
	private Animation inAnimation;
	private Animation outAnimation;
	private LayoutParams layoutParams;
	private int duration;
	private Timer timer = new Timer();
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public void setView(View view)
	{
		this.view = view;
	}
	
	public View getView()
	{
		return this.view;
	}
	
	public void setInAnimation(Animation inAnimation)
	{
		this.inAnimation = inAnimation;
	}
	
	public Animation getInAnimation()
	{
		return this.inAnimation;
	}
	
	public void setOutAnimation(Animation outAnimation)
	{
		this.outAnimation = outAnimation;
	}
	
	public Animation getOutAnimation()
	{
		return this.outAnimation;
	}

	public void setLayoutParams(LayoutParams layoutParams)
	{
		this.layoutParams = layoutParams;
	}
	
	public LayoutParams getLayoutParams()
	{
		return this.layoutParams;
	}
	
	public void setDuration(int duration)
	{
		this.duration = duration;
	}
	
	public int getDuration()
	{
		return this.duration;
	}
	
	public void setTimer(Timer timer)
	{
		this.timer = timer;
	}
	
	public Timer getTimer()
	{
		return this.timer;
	}
}
