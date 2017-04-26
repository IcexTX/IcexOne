package com.example.icex.icexone.view;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;

import com.example.icex.icexone.R;


public class RoundImageView extends android.support.v7.widget.AppCompatImageView {

	// 传入上下文
	private Context context;
	// 控制默认的长、
	private int defaultwidth = 0;
	private int defaulheight = 0;
	// 外边圆，如果只有其中一个有值，则只画一个圆形边框
	private int Borderoutdecolor = 0;
	private int Borderinsidecolor = 0;
	// 默认值
	private int defaultColor = 0xFFFFFFFF;
	private int BorderThickness = 0;

	public RoundImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public RoundImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
		//实例化
		setCustomAttributeSet(attrs);
	}



	public RoundImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		this.context = context;
		setCustomAttributeSet(attrs);
	}

	/**
	 * TypedArray实例是个属性的容器 context.obtainStyledAttributes()方法返回得到
	 * AttributeSet是节点的属性集合] 将获取自定义textSize的值，如果没有，则使用默认的值，15。
	 * @param attrs
	 */
	private void setCustomAttributeSet(AttributeSet attrs) {
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.roundedimageview);
		BorderThickness = typedArray.getDimensionPixelSize(R.styleable.roundedimageview_border_thickness, 0);
		Borderoutdecolor = typedArray.getColor(R.styleable.roundedimageview_border_outside_color,defaultColor);
		Borderinsidecolor = typedArray.getColor(R.styleable.roundedimageview_border_inside_color, defaultColor);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Drawable drawable = getDrawable();
		if (drawable == null) {
			return;
		}
		if (getWidth() == 0 || getHeight() == 0) {
			return;
		}
		this.measure(0, 0);
		if (drawable.getClass() == NinePatchDrawable.class) {
			return;
		}
		Bitmap bit = ((BitmapDrawable) drawable).getBitmap();
		Bitmap bitmap = bit.copy(Bitmap.Config.ARGB_8888, true);
		if (defaultwidth == 0) {
			defaultwidth = getWidth();
		}
		if (defaulheight == 0) {
			defaulheight = getHeight();
		}

		/**
		 * 此方法为保证重新读取图片后不会因为图片的大小而改变控件宽、高的大小
		 * （针对宽、高为wrap_content布局的imageview，但会导致margin无效）
		 */
		/*
		 if(defaultwidth != 0 && defaulheight != 0){
		  LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(defaultwidth,defaulheight);
		  setLayoutParams(params);
		    }
		 */
		int radius = 0;
		// 定义画两个边框，分别为外圆边框和内圆边框
		if (Borderinsidecolor != defaultColor&& Borderoutdecolor != defaultColor) {
			radius = (defaultwidth < defaulheight ? defaultwidth : defaulheight)/ 2 - 2 * BorderThickness;
			// 画出内圆
			drawCircleBorder(canvas, radius + BorderThickness / 2,Borderinsidecolor);
			// 画出外圆
			drawCircleBorder(canvas, radius + BorderThickness + BorderThickness/ 2, Borderoutdecolor);
		}
		// 定义画一个内圆边框
		else if (Borderinsidecolor != defaultColor&& Borderoutdecolor == defaultColor) {
			radius = (defaultwidth < defaulheight ? defaultwidth : defaulheight)/ 2 - BorderThickness;
			drawCircleBorder(canvas, radius + BorderThickness / 2,Borderinsidecolor);
		}
		// 定义画一个外圆边框
		else if (Borderinsidecolor == defaultColor&& Borderoutdecolor != defaultColor) {
			radius = (defaultwidth < defaulheight ? defaultwidth : defaulheight)/ 2 - BorderThickness;
			drawCircleBorder(canvas, radius + BorderThickness + 2,Borderoutdecolor);
		}
		// 没有外圆边框
		else {
			radius = (defaultwidth < defaulheight ? defaultwidth : defaulheight) / 2;
		}
		Bitmap roundBitmap = getCroppedRoundBitmap(bitmap, radius);
		canvas.drawBitmap(roundBitmap, defaultwidth / 2 - radius, defaulheight/ 2 - radius, null);
	}

	/**
	 * 边缘画圆
	 *
	 * @param canvas
	 * @param radius
	 * @param Color
	 */
	private void drawCircleBorder(Canvas canvas, int radius, int Color) {
		// 声明画笔
		Paint paint = new Paint();
		// 消除锯齿
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
		// 设置画笔颜色
		paint.setColor(Color);
		// 设置画笔的样式为空心
		paint.setStyle(Paint.Style.STROKE);
		// 设置画笔的外框宽度
		paint.setStrokeWidth(BorderThickness);
		// 画出圆
		canvas.drawCircle(defaultwidth / 2, defaulheight / 2, radius, paint);
	}

	/**
	 * 获取剪裁后的圆形图片
	 */
	public Bitmap getCroppedRoundBitmap(Bitmap bitmap, int radius) {
		Bitmap scaledSrcBitmap;
		int diameter = radius * 2;

		// 为了防止宽高不相等，造成圆形图片变形，因此截取长方形中处于中间位置最大的正方形图片
		int bmpWidth = bitmap.getWidth();
		int bmpHeight = bitmap.getHeight();
		int squareWidth = 0, squareHeight = 0;
		int x = 0, y = 0;
		Bitmap squareBitmap;
		if (bmpHeight > bmpWidth) {// 高大于宽
			squareWidth = squareHeight = bmpWidth;
			x = 0;
			y = (bmpHeight - bmpWidth) / 2;
			// 截取正方形图片
			squareBitmap = Bitmap.createBitmap(bitmap, x, y, squareWidth,squareHeight);
		} else if (bmpHeight < bmpWidth) {// 宽大于高
			squareWidth = squareHeight = bmpHeight;
			x = (bmpWidth - bmpHeight) / 2;
			y = 0;
			squareBitmap = Bitmap.createBitmap(bitmap, x, y, squareWidth,
					squareHeight);
		} else {
			squareBitmap = bitmap;
		}

		if (squareBitmap.getWidth() != diameter
				|| squareBitmap.getHeight() != diameter) {
			scaledSrcBitmap = Bitmap.createScaledBitmap(squareBitmap, diameter,
					diameter, true);

		} else {
			scaledSrcBitmap = squareBitmap;
		}
		Bitmap output = Bitmap.createBitmap(scaledSrcBitmap.getWidth(),
				scaledSrcBitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		Paint paint = new Paint();
		Rect rect = new Rect(0, 0, scaledSrcBitmap.getWidth(),
				scaledSrcBitmap.getHeight());

		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
		canvas.drawARGB(0, 0, 0, 0);
		canvas.drawCircle(scaledSrcBitmap.getWidth() / 2,
				scaledSrcBitmap.getHeight() / 2,
				scaledSrcBitmap.getWidth() / 2, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(scaledSrcBitmap, rect, rect, paint);
		// bitmap回收(recycle导致在布局文件XML看不到效果)
		// bmp.recycle();
		// squareBitmap.recycle();
		// scaledSrcBmp.recycle();
		bitmap = null;
		squareBitmap = null;
		scaledSrcBitmap = null;
		return output;

	}
}
