package com.cgq.cardslide.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UnlockAppView extends View {

	private static final String TAG = "UnlockAppView";

	/**屏幕宽 */
	private int width;
	/**屏幕高度*/
	private int height;

	private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

	private Paint mPaint0 = new Paint(Paint.ANTI_ALIAS_FLAG);

	private Paint mPaintCancelH = new Paint(Paint.ANTI_ALIAS_FLAG);

	private Paint mPaintOKH = new Paint(Paint.ANTI_ALIAS_FLAG);

	private Paint mPaintOKM = new Paint(Paint.ANTI_ALIAS_FLAG);

	private Paint mPaintCancelM = new Paint(Paint.ANTI_ALIAS_FLAG);

	private Paint mPaintPrompt = new Paint(Paint.ANTI_ALIAS_FLAG);

	private TextPaint mTPaintPrompt = new TextPaint(Paint.ANTI_ALIAS_FLAG);

	private float rH;

	private int rM;

	private int rL;

	private float a1;

	private float b1;

	private float a2;

	private float b2;

	private float a3;

	private float b3;

	private float a4;

	private float b4;

	private float a5;

	private float b5;

	private float a6;

	private float b6;

	private float a7;

	private float b7;

	private float a8;

	private float b8;

	private float a9;

	private float b9;

	private RectF rt1;

	private RectF rt2;

	private RectF rt3;

	private RectF rt4;

	private RectF rt5;

	private RectF rt6;

	private RectF rt7;

	private RectF rt8;

	private RectF rt9;

	boolean bool1 = false;

	boolean bool2 = false;

	boolean bool3 = false;

	boolean bool4 = false;

	boolean bool5 = false;

	boolean bool6 = false;

	boolean bool7 = false;

	boolean bool8 = false;

	boolean bool9 = false;

	int num = 0;

	/**绘制的图形的文本密码*/
	private String passwordValue = "";

	private Context mContext;
	/**判断是否已经设置了密码*/
	private boolean isSetPasswordSuccess;

	private String previousPasswordValue = "";

	private int textRatio;

	private static final int nexusWidth = 768;

	private static final int nexusHeight = 1184;

	private static final int nexusDensity = 2;

	public UnlockAppView(Context context) {
		super(context);
		mContext = context;
		isSetPasswordSuccess = false;
		setDisplay();
		initParam();
	}

	public UnlockAppView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		isSetPasswordSuccess = false;
		setDisplay();
		initParam();
	}

	private void initParam() {
		if (!isSetPasswordSuccess) {
			prompt = "";
		}
		setFocusable(true);
		setFocusableInTouchMode(true);
		rH = (width / 3) / 5;
		rM = (width / 3) / 10;
		rL = (width / 3) / 12;
		a1 = (width / 3) / 2;
		b1 = (width / 3) / 2 + (height - width) / 2;
		a2 = (width / 3) + (width / 3) / 2;
		b2 = b1;
		a3 = (width / 3) * 2 + (width / 3) / 2;
		b3 = b1;
		a4 = a1;
		b4 = (width / 3) + (width / 3) / 2 + (height - width) / 2;
		a5 = a2;
		b5 = b4;
		a6 = a3;
		b6 = b4;
		a7 = a1;
		b7 = (width / 3) * 2 + (width / 3) / 2 + (height - width) / 2;
		a8 = a5;
		b8 = b7;
		a9 = a6;
		b9 = b7;
		rt1 = new RectF(a1 - rH, b1 - rH, a1 + rH, b1 + rH);
		rt2 = new RectF(a2 - rH, b2 - rH, a2 + rH, b2 + rH);
		rt3 = new RectF(a3 - rH, b3 - rH, a3 + rH, b3 + rH);
		rt4 = new RectF(a4 - rH, b4 - rH, a4 + rH, b4 + rH);
		rt5 = new RectF(a5 - rH, b5 - rH, a5 + rH, b5 + rH);
		rt6 = new RectF(a6 - rH, b6 - rH, a6 + rH, b6 + rH);
		rt7 = new RectF(a7 - rH, b7 - rH, a7 + rH, b7 + rH);
		rt8 = new RectF(a8 - rH, b8 - rH, a8 + rH, b8 + rH);
		rt9 = new RectF(a9 - rH, b9 - rH, a9 + rH, b9 + rH);
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.LTGRAY);
		mPaint0.setAntiAlias(true);
		mPaint0.setColor(Color.WHITE);
		mPaintOKM.setAntiAlias(true);
		mPaintOKM.setColor(Color.GREEN);
		mPaintOKM.setStrokeWidth(rM);
		mPaintOKH.setAntiAlias(true);
		mPaintOKH.setColor(Color.GREEN);
		mPaintOKH.setStyle(Paint.Style.STROKE);
		mPaintOKH.setStrokeWidth(5);
		mPaintCancelM.setAntiAlias(true);
		mPaintCancelM.setColor(Color.RED);
		mPaintCancelM.setStrokeWidth(rM);
		mPaintCancelH.setAntiAlias(true);
		mPaintCancelH.setStyle(Paint.Style.STROKE);
		mPaintCancelH.setStrokeWidth(5);
		mPaintCancelH.setColor(Color.RED);
		mPaintPrompt.setAntiAlias(true);
		mPaintPrompt.setColor(Color.BLACK);
		mPaintPrompt.setTextSize(30.0f);
		mPaintPrompt.setTextAlign(Align.CENTER);

		mTPaintPrompt.setAntiAlias(true);
		mTPaintPrompt.setColor(Color.BLACK);
		mTPaintPrompt.setTextSize(textRatio);
		mTPaintPrompt.setTextAlign(Align.CENTER);
		rfClip = new RectF(0, b1 - (width / 3) / 2, width, b9 + (width / 3) / 2);
	}

	private void setDisplay() {
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		width = metric.widthPixels;
		height = metric.heightPixels;
		float ratioWidth = (float) width / nexusWidth;
		float ratioHeight = (float) height / nexusHeight;
		float ratio = Math.max(ratioWidth, ratioHeight);

		textRatio = (int) Math.round(30 * Math.sqrt(ratio * metric.density
				/ nexusDensity));
		height -= 100;
	}

	private RectF rfClip;

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		canvas.translate(width / 2, (b1 - (width / 3) + 100) / 2);

		canvas.drawText(prompt, 1.0f, rt1.height() / 2, mTPaintPrompt);
		canvas.translate(-(width / 2), -((b1 - (width / 3) + 100) / 2));
		canvas.clipRect(rfClip);
		if (num < 4 && num > 0) {
			for (int i = 0; i < stopXs.size(); i++) {
				canvas.drawLine(startXs.get(i), startYs.get(i), stopXs.get(i),
						stopYs.get(i), mPaintCancelM);
			}
			canvas.drawLine(startX, startY, stopX, stopY, mPaintCancelM);
		} else if (num >= 4) {
			for (int i = 0; i < stopXs.size(); i++) {
				canvas.drawLine(startXs.get(i), startYs.get(i), stopXs.get(i),
						stopYs.get(i), mPaintOKM);
			}
			canvas.drawLine(startX, startY, stopX, stopY, mPaintOKM);
		}
		canvas.drawCircle(a1, b1, rH, mPaint);
		canvas.drawCircle(a2, b2, rH, mPaint);
		canvas.drawCircle(a3, b3, rH, mPaint);
		canvas.drawCircle(a4, b4, rH, mPaint);
		canvas.drawCircle(a5, b5, rH, mPaint);
		canvas.drawCircle(a6, b6, rH, mPaint);
		canvas.drawCircle(a7, b7, rH, mPaint);
		canvas.drawCircle(a8, b8, rH, mPaint);
		canvas.drawCircle(a9, b9, rH, mPaint);
		canvas.drawCircle(a1, b1, rM, mPaint0);
		canvas.drawCircle(a2, b2, rM, mPaint0);
		canvas.drawCircle(a3, b3, rM, mPaint0);
		canvas.drawCircle(a4, b4, rM, mPaint0);
		canvas.drawCircle(a5, b5, rM, mPaint0);
		canvas.drawCircle(a6, b6, rM, mPaint0);
		canvas.drawCircle(a7, b7, rM, mPaint0);
		canvas.drawCircle(a8, b8, rM, mPaint0);
		canvas.drawCircle(a9, b9, rM, mPaint0);
		if (bool1) {// (a1,b1)
			canvas.drawCircle(a1, b1, rL, mPaintOKM);
			canvas.drawCircle(a1, b1, rH, mPaintOKH);
		}
		if (bool2) {// (a2,b2)
			canvas.drawCircle(a2, b2, rL, mPaintOKM);
			canvas.drawCircle(a2, b2, rH, mPaintOKH);
		}
		if (bool3) {// (a3,b3)
			canvas.drawCircle(a3, b3, rL, mPaintOKM);
			canvas.drawCircle(a3, b3, rH, mPaintOKH);
		}
		if (bool4) {// (a4,b4)
			canvas.drawCircle(a4, b4, rL, mPaintOKM);
			canvas.drawCircle(a4, b4, rH, mPaintOKH);
		}
		if (bool5) {// (a5,b5)
			canvas.drawCircle(a5, b5, rL, mPaintOKM);
			canvas.drawCircle(a5, b5, rH, mPaintOKH);
		}
		if (bool6) {// (a6,b6)
			canvas.drawCircle(a6, b6, rL, mPaintOKM);
			canvas.drawCircle(a6, b6, rH, mPaintOKH);
		}
		if (bool7) {// (a7,b7)
			canvas.drawCircle(a7, b7, rL, mPaintOKM);
			canvas.drawCircle(a7, b7, rH, mPaintOKH);
		}
		if (bool8) {// (a8,b8)
			canvas.drawCircle(a8, b8, rL, mPaintOKM);
			canvas.drawCircle(a8, b8, rH, mPaintOKH);
		}
		if (bool9) {// (a9,b9)
			canvas.drawCircle(a9, b9, rL, mPaintOKM);
			canvas.drawCircle(a9, b9, rH, mPaintOKH);
		}

	}

	private List<Float> startXs = new ArrayList<>();

	private List<Float> startYs = new ArrayList<>();

	private List<Float> stopXs = new ArrayList<>();

	private List<Float> stopYs = new ArrayList<>();

	private float startX;

	private float startY;

	private float stopX;

	private float stopY;

	private String prompt = "";

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			bool1 = false;
			bool2 = false;
			bool3 = false;
			bool4 = false;
			bool5 = false;
			bool6 = false;
			bool7 = false;
			bool8 = false;
			bool9 = false;
			num = 0;
			passwordValue = "";
			startXs.clear();
			startYs.clear();
			stopXs.clear();
			stopYs.clear();
			invalidate();// 刷新界面
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			bool1 = false;
			bool2 = false;
			bool3 = false;
			bool4 = false;
			bool5 = false;
			bool6 = false;
			bool7 = false;
			bool8 = false;
			bool9 = false;
			num = 0;
			startXs.clear();
			startYs.clear();
			stopXs.clear();
			stopYs.clear();
			startX = 0;
			startY = 0;
			if (isSetPasswordSuccess) {
				if (previousPasswordValue.compareTo(passwordValue) == 0) {
					//解锁成功
					Toast.makeText(mContext,"解锁成功，密码："+passwordValue,Toast.LENGTH_SHORT).show();
				} else if ("".compareTo(passwordValue) != 0) {
					prompt = "";
					invalidate();// 刷新界面
					Toast.makeText(mContext,"解锁失败！",Toast.LENGTH_SHORT).show();
				}
				return true;
			} else {
				if ("".compareTo(passwordValue) == 0) {
					return true;
				} else if (passwordValue.length() < 4) {
					Log.e(TAG, "onTouchEvent: ---------passwordValue-----小于4位");
					prompt = "";
					previousPasswordValue = "";
				} else if ("".compareTo(previousPasswordValue) == 0) {
					Log.e(TAG, "onTouchEvent: ---------previousPasswordValue-----等于空的");
					previousPasswordValue = passwordValue;
					Toast.makeText(mContext,"请再次绘制图形！",Toast.LENGTH_SHORT).show();
					prompt = "";
				} else if (passwordValue.compareTo(previousPasswordValue) != 0) {
					Log.e(TAG, "onTouchEvent: ---------两次设置的密码不一致");
					Toast.makeText(mContext,"两次图形不一致，重新绘制",Toast.LENGTH_LONG).show();
					prompt = "";
					previousPasswordValue = "";
				} else {
					isSetPasswordSuccess = true;
					Toast.makeText(mContext,"密码设置成功！",Toast.LENGTH_SHORT).show();
					Log.e(TAG, "onTouchEvent: -------密码设置成功---------："+previousPasswordValue);
				}
				passwordValue = "";
				invalidate();// 刷新界面
			}
		} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			float tX = event.getX();
			float tY = event.getY();
			stopX = tX;
			stopY = tY;
			if (rt1.contains(tX, tY) && !bool1) {
				setMiddlePint(a1, b1);
				bool1 = true;
				if (num != 0) {
					stopXs.add(a1);
					stopYs.add(b1);
				}
				num += 1;
				passwordValue += "A";
				startX = a1;
				startY = b1;
				startXs.add(a1);
				startYs.add(b1);

			} else if (rt2.contains(tX, tY) && !bool2) {
				setMiddlePint(a2, b2);
				bool2 = true;
				if (num != 0) {
					stopXs.add(a2);
					stopYs.add(b2);
				}
				num += 1;
				passwordValue += "B";
				startX = a2;
				startY = b2;
				startXs.add(a2);
				startYs.add(b2);
			} else if (rt3.contains(tX, tY) && !bool3) {
				setMiddlePint(a3, b3);
				bool3 = true;
				if (num != 0) {
					stopXs.add(a3);
					stopYs.add(b3);
				}
				num += 1;
				passwordValue += "C";
				startX = a3;
				startY = b3;
				startXs.add(a3);
				startYs.add(b3);
			} else if (rt4.contains(tX, tY) && !bool4) {
				setMiddlePint(a4, b4);
				bool4 = true;
				if (num != 0) {
					stopXs.add(a4);
					stopYs.add(b4);
				}
				num += 1;
				passwordValue += "D";
				startX = a4;
				startY = b4;
				startXs.add(a4);
				startYs.add(b4);
			} else if (rt5.contains(tX, tY) && !bool5) {
				setMiddlePint(a5, b5);
				bool5 = true;
				if (num != 0) {
					stopXs.add(a5);
					stopYs.add(b5);
				}
				num += 1;
				passwordValue += "E";
				startX = a5;
				startY = b5;
				startXs.add(a5);
				startYs.add(b5);
			} else if (rt6.contains(tX, tY) && !bool6) {
				setMiddlePint(a6, b6);
				bool6 = true;
				if (num != 0) {
					stopXs.add(a6);
					stopYs.add(b6);
				}
				num += 1;
				passwordValue += "F";
				startX = a6;
				startY = b6;
				startXs.add(a6);
				startYs.add(b6);
			} else if (rt7.contains(tX, tY) && !bool7) {
				setMiddlePint(a7, b7);
				bool7 = true;
				if (num != 0) {
					stopXs.add(a7);
					stopYs.add(b7);
				}
				num += 1;
				passwordValue += "G";
				startX = a7;
				startY = b7;
				startXs.add(a7);
				startYs.add(b7);
			} else if (rt8.contains(tX, tY) && !bool8) {
				setMiddlePint(a8, b8);
				bool8 = true;
				if (num != 0) {
					stopXs.add(a8);
					stopYs.add(b8);
				}
				num += 1;
				passwordValue += "H";
				startX = a8;
				startY = b8;
				startXs.add(a8);
				startYs.add(b8);
			} else if (rt9.contains(tX, tY) && !bool9) {
				setMiddlePint(a9, b9);
				bool9 = true;
				if (num != 0) {
					stopXs.add(a9);
					stopYs.add(b9);
				}
				num += 1;
				passwordValue += "I";
				startX = a9;
				startY = b9;
				startXs.add(a9);
				startYs.add(b9);
			}
			invalidate();// 刷新界面
		}
		return true;
	}

	private void setMiddlePint(float stopX, float stopY) {
		if (startX != 0 && stopY != 0) {
			float midpointX = Math.abs(stopX + startX) / 2;
			float midpointY = Math.abs(stopY + startY) / 2;
			if (midpointX == a2 && midpointY == b2 && !bool2) {
				bool2 = true;
				num += 1;
				passwordValue += "B";
			} else if (midpointX == a4 && midpointY == b4 && !bool4) {
				bool4 = true;
				num += 1;
				passwordValue += "D";
			} else if (midpointX == a5 && midpointY == b5 && !bool5) {
				bool5 = true;
				num += 1;
				passwordValue += "E";
			} else if (midpointX == a6 && midpointY == b6 && !bool6) {
				bool6 = true;
				num += 1;
				passwordValue += "F";
			} else if (midpointX == a8 && midpointY == b8 && !bool8) {
				bool8 = true;
				num += 1;
				passwordValue += "H";
			}
		}
	}
}
