package com.peng.weibo.widget.imageView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.peng.weibo.R;

/**
 * Created by PS on 2016/7/20.
 */
public class CircleImageView extends ImageView {

	Path path;
	public PaintFlagsDrawFilter mPaintFlagsDrawFilter;// 毛边过滤
	Paint paint;

	public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	public CircleImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public CircleImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	public void init() {
		mPaintFlagsDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setColor(getResources().getColor(R.color.colorPrimary));
	}

	@Override
	protected void onDraw(Canvas cns) {
		// TODO Auto-generated method stub
		float h = getMeasuredHeight() - 3.0f;
		float w = getMeasuredWidth() - 3.0f;
		if (path == null) {
			path = new Path();
			path.addCircle(w / 2.0f, h / 2.0f, (float) Math.min(w / 2.0f, (h / 2.0)), Path.Direction.CCW);
			path.close();
		}
		cns.drawCircle(w / 2.0f, h / 2.0f, Math.min(w / 2.0f, h / 2.0f) + 1.5f, paint);
		int saveCount = cns.getSaveCount();
		cns.save();
		cns.setDrawFilter(mPaintFlagsDrawFilter);
		cns.clipPath(path, Region.Op.REPLACE);
		cns.setDrawFilter(mPaintFlagsDrawFilter);
		cns.drawColor(getResources().getColor(R.color.colorPrimary));
		super.onDraw(cns);
		cns.restoreToCount(saveCount);
	}

	@Override
	public void setImageBitmap(Bitmap bm) {
		super.setImageBitmap(toRoundBitmap(bm));
	}

	/**
	 * 转换图片成圆形
	 * @param bitmap 传入Bitmap对象
	 * @return
	 */
	public Bitmap toRoundBitmap(Bitmap bitmap)
	{
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float roundPx;
		float left,top,right,bottom,dst_left,dst_top,dst_right,dst_bottom;
		if (width <= height) {
			roundPx = width / 2 -5;
			top = 0;
			bottom = width;
			left = 0;
			right = width;
			height = width;
			dst_left = 0;
			dst_top = 0;
			dst_right = width;
			dst_bottom = width;
		} else {
			roundPx = height / 2 -5;
			float clip = (width - height) / 2;
			left = clip;
			right = width - clip;
			top = 0;
			bottom = height;
			width = height;
			dst_left = 0;
			dst_top = 0;
			dst_right = height;
			dst_bottom = height;
		}

		Bitmap output = Bitmap.createBitmap(width,
				height, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect src = new Rect((int)left, (int)top, (int)right, (int)bottom);
		final Rect dst = new Rect((int)dst_left, (int)dst_top, (int)dst_right, (int)dst_bottom);
		final RectF rectF = new RectF(dst_left+15, dst_top+15, dst_right-20, dst_bottom-20);

		paint.setAntiAlias(true);

		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);

		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, src, dst, paint);
		return output;
	}
}
