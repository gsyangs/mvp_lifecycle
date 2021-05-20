package com.app.jetp2.common;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import com.app.jetp2.R;

import net.frakbot.jumpingbeans.JumpingBeans;

import java.text.DecimalFormat;

/**
 * @author ys
 */
public class LoadingDialog extends Dialog {
	private Context mContext;
	private boolean mCancelable;
    private String title;
    private boolean showLoadingPercent;
    private TextView percentTxt;
	private ImageView loadingView;
	private DecimalFormat decimalFormat;
	private TextView loadingTitle;
	private JumpingBeans jumpingBeans;

	public LoadingDialog(Context context) {
		super(context, R.style.dialog);
		mContext = context;
	}
	public LoadingDialog(Context context, String title) {
        super(context, R.style.dialog);
        mContext = context;
        this.title = title;
    }
	public LoadingDialog(Context context, boolean cancelable) {
		super(context, R.style.dialog);
		mContext = context;
		mCancelable = cancelable;
	}
	
	public LoadingDialog(Context context, String title, boolean showLoadingPercent, boolean cancelable) {
        super(context, R.style.dialog);
        mContext = context;
		mCancelable = cancelable;
		this.title = title;
		this.showLoadingPercent = showLoadingPercent;
		decimalFormat = new DecimalFormat("#.##"); //小数点格式化对象
    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading_dialog_layout);
		this.setCanceledOnTouchOutside(false);
		this.setCancelable(mCancelable);
		loadingView = findViewById(R.id.loading_image);
		loadingTitle = findViewById(R.id.loading_title);
		if(this.title != null && !"".equals(title)) {
			loadingTitle.setText(title);
		}
		if (jumpingBeans == null){
			jumpingBeans = JumpingBeans.with(loadingTitle)
					.makeTextJump(0, loadingTitle.getText().length())
					.setIsWave(true)
					.setLoopDuration(1000)
					.build();
		}
		if(showLoadingPercent) {
		    percentTxt = findViewById(R.id.loading_percent);
		    percentTxt.setVisibility(View.VISIBLE);
		}
	}
	public void setPercent(double percent) {
	    if(percentTxt == null) {
        return;
	    }
		String showValue = decimalFormat.format(percent) + "%";
		percentTxt.setText(showValue);
	}
	public void setTitle(String title){
		if(title == null) {
			return;
		}
		loadingTitle.setText(title);
	}

	@Override
	public void show() {
		super.show();
		loadingView.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.progress_anim));
	}
}
