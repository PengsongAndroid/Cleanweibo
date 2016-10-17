package com.peng.weibo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.peng.weibo.R;
import com.peng.weibo.data.entity.Status;
import com.peng.weibo.util.common.Constants;
import com.peng.weibo.util.common.TransferUtil;
import com.peng.weibo.util.tools.Logs;
import com.peng.weibo.widget.emojitextview.EmojiTextView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by PS on 2016/8/1.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

	/**
	 * Views indexed with their IDs
	 */
	private final SparseArray<View> views;

	public View convertView;

	/**
	 * Package private field to retain the associated user object and detect a
	 * change
	 */
	Object associatedObject;

	protected BaseViewHolder(View view) {
		super(view);
		this.views = new SparseArray<View>();
		convertView = view;

	}

	public View getConvertView() {

		return convertView;
	}

	/**
	 * Will set the text of a TextView.
	 *
	 * @param viewId
	 *            The view id.
	 * @param value
	 *            The text to put in the text view.
	 * @return The BaseViewHolder for chaining.
	 */
	public BaseViewHolder setText(int viewId, CharSequence value) {
		TextView view = getView(viewId);
		view.setText(value);
		return this;
	}

	/**
         *
         */
	public BaseViewHolder setEmojiText(int viewId, String value, Context context) {
		EmojiTextView view = getView(viewId);
		view.setText(TransferUtil.getWeiBoContent(value, context, view));
		return this;
	}

	/**
	 * Will set the image of an ImageView from a resource id.
	 *
	 * @param viewId
	 *            The view id.
	 * @param imageResId
	 *            The image resource id.
	 * @return The BaseViewHolder for chaining.
	 */
	public BaseViewHolder setImageResource(int viewId, int imageResId) {
		ImageView view = getView(viewId);
		view.setImageResource(imageResId);
		return this;
	}

	/**
	 * Will set background color of a view.
	 *
	 * @param viewId
	 *            The view id.
	 * @param color
	 *            A color, not a resource id.
	 * @return The BaseViewHolder for chaining.
	 */
	public BaseViewHolder setBackgroundColor(int viewId, int color) {
		View view = getView(viewId);
		view.setBackgroundColor(color);
		return this;
	}

	/**
	 * Will set background of a view.
	 *
	 * @param viewId
	 *            The view id.
	 * @param backgroundRes
	 *            A resource to use as a background.
	 * @return The BaseViewHolder for chaining.
	 */
	public BaseViewHolder setBackgroundRes(int viewId, int backgroundRes) {
		View view = getView(viewId);
		view.setBackgroundResource(backgroundRes);
		return this;
	}

	/**
	 * Will set text color of a TextView.
	 *
	 * @param viewId
	 *            The view id.
	 * @param textColor
	 *            The text color (not a resource id).
	 * @return The BaseViewHolder for chaining.
	 */
	public BaseViewHolder setTextColor(int viewId, int textColor) {
		TextView view = getView(viewId);
		view.setTextColor(textColor);
		return this;
	}

	/**
	 * Will set the image of an ImageView from a drawable.
	 *
	 * @param viewId
	 *            The view id.
	 * @param drawable
	 *            The image drawable.
	 * @return The BaseViewHolder for chaining.
	 */
	public BaseViewHolder setImageDrawable(int viewId, Drawable drawable) {
		ImageView view = getView(viewId);
		view.setImageDrawable(drawable);
		return this;
	}

	/**
	 * Add an action to set the image of an image view. Can be called multiple
	 * times.
	 */
	public BaseViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
		ImageView view = getView(viewId);
		view.setImageBitmap(bitmap);
		return this;
	}

	/**
	 * 通过url加载图片
	 */
	public BaseViewHolder setImageBitmap(int viewId, String url, Context context) {
		ImageView view = getView(viewId);
		Picasso.with(context).load(url).into(view);
		return this;
	}

	/**
	 * 加载微博内容图片
	 */
	public BaseViewHolder setImageList(final int viewId, ArrayList<Status.PicUrlsBean> url, final Context context) {
		if (url.size() > 0) {
			RecyclerView imageList = getView(viewId);
			ArrayList<String> urls = new ArrayList<String>();
			for (int i = 0; i < url.size(); i++) {
				urls.add(url.get(i).thumbnail_pic);
			}
			final GridLayoutManager gridLayoutManager = initGridLayoutManager(urls, context);
			imageList.setVisibility(View.GONE);
			imageList.setVisibility(View.VISIBLE);
			imageList.requestLayout();
			imageList.setLayoutManager(gridLayoutManager);
			imageList.setHasFixedSize(true);
			imageList.setAdapter(new BaseQuickAdapter<Status.PicUrlsBean>(R.layout.item_image, url) {

				@Override
				protected void convert(BaseViewHolder helper, Status.PicUrlsBean item) {
//					helper.setImageBitmap(R.id.item_image, item.thumbnail_pic, context);

				}
			});
		}

		// if (url.size() > 0){
		// Logs.e("size " + url.size());
		// }
		// final RecyclerView imageList = getView(viewId);
		// imageList.setVisibility(View.VISIBLE);
		// ArrayList<String> urlList = new ArrayList<String>();
		// for (int i = 0; i < url.size(); i++){
		// urlList.add(url.get(i).thumbnail_pic);
		// }
		// imageList.setLayoutManager(initGridLayoutManager(urlList, context));
		// final ImageView view = new ImageView(context);
		// //
		// view.setImageDrawable(context.getResources().getDrawable(R.mipmap.message_image_default));
		// // imageList.addView(view);
		// Target target = new Target() {
		// @Override
		// public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
		// Logs.e("onBitmapLoaded");
		// view.setImageBitmap(bitmap);
		// // imageList.removeAllViews();
		// imageList.
		// imageList.addView(view, 1, RecyclerView.LayoutParams.WRAP_CONTENT);
		// }
		//
		// @Override
		// public void onBitmapFailed(Drawable errorDrawable) {
		// Logs.e("onBitmapFailed");
		// }
		//
		// @Override
		// public void onPrepareLoad(Drawable placeHolderDrawable) {
		// Logs.e("onPrepareLoad");
		// }
		// };
		// for (int i = 0; i < url.size(); i++) {
		// Picasso.with(context).load(urlList.get(i)).into(target);
		// }
		return this;
	}

	public void setWeiboItemImage(){

	}

	/**
	 * 根据图片数量，初始化GridLayoutManager，并且设置列数， 当图片 = 1 的时候，显示1列 当图片<=4张的时候，显示2列
	 * 当图片>4 张的时候，显示3列
	 *
	 * @return
	 */
	private static GridLayoutManager initGridLayoutManager(ArrayList<String> imageDatas, Context context) {
		GridLayoutManager gridLayoutManager;
		if (imageDatas != null) {
			switch (imageDatas.size()) {
			case 1:
				gridLayoutManager = new GridLayoutManager(context, 1);
				break;
			case 2:
				gridLayoutManager = new GridLayoutManager(context, 2);
				break;
			case 3:
				gridLayoutManager = new GridLayoutManager(context, 3);
				break;
			case 4:
				gridLayoutManager = new GridLayoutManager(context, 2);
				break;
			default:
				gridLayoutManager = new GridLayoutManager(context, 3);
				break;
			}
		} else {
			gridLayoutManager = new GridLayoutManager(context, 3);
		}
		return gridLayoutManager;
	}

	/**
	 * Add an action to set the alpha of a view. Can be called multiple times.
	 * Alpha between 0-1.
	 */
	public BaseViewHolder setAlpha(int viewId, float value) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getView(viewId).setAlpha(value);
		} else {
			// Pre-honeycomb hack to set Alpha value
			AlphaAnimation alpha = new AlphaAnimation(value, value);
			alpha.setDuration(0);
			alpha.setFillAfter(true);
			getView(viewId).startAnimation(alpha);
		}
		return this;
	}

	/**
	 * Set a view visibility to VISIBLE (true) or GONE (false).
	 *
	 * @param viewId
	 *            The view id.
	 * @param visible
	 *            True for VISIBLE, false for GONE.
	 * @return The BaseViewHolder for chaining.
	 */
	public BaseViewHolder setVisible(int viewId, boolean visible) {
		View view = getView(viewId);
		view.setVisibility(visible ? View.VISIBLE : View.GONE);
		return this;
	}

	/**
	 * Add links into a TextView.
	 *
	 * @param viewId
	 *            The id of the TextView to linkify.
	 * @return The BaseViewHolder for chaining.
	 */
	public BaseViewHolder linkify(int viewId) {
		TextView view = getView(viewId);
		Linkify.addLinks(view, Linkify.ALL);
		return this;
	}

	/**
	 * Apply the typeface to the given viewId, and enable subpixel rendering.
	 */
	public BaseViewHolder setTypeface(int viewId, Typeface typeface) {
		TextView view = getView(viewId);
		view.setTypeface(typeface);
		view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
		return this;
	}

	/**
	 * Apply the typeface to all the given viewIds, and enable subpixel
	 * rendering.
	 */
	public BaseViewHolder setTypeface(Typeface typeface, int... viewIds) {
		for (int viewId : viewIds) {
			TextView view = getView(viewId);
			view.setTypeface(typeface);
			view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
		}
		return this;
	}

	/**
	 * Sets the progress of a ProgressBar.
	 *
	 * @param viewId
	 *            The view id.
	 * @param progress
	 *            The progress.
	 * @return The BaseViewHolder for chaining.
	 */
	public BaseViewHolder setProgress(int viewId, int progress) {
		ProgressBar view = getView(viewId);
		view.setProgress(progress);
		return this;
	}

	/**
	 * Sets the progress and max of a ProgressBar.
	 *
	 * @param viewId
	 *            The view id.
	 * @param progress
	 *            The progress.
	 * @param max
	 *            The max value of a ProgressBar.
	 * @return The BaseViewHolder for chaining.
	 */
	public BaseViewHolder setProgress(int viewId, int progress, int max) {
		ProgressBar view = getView(viewId);
		view.setMax(max);
		view.setProgress(progress);
		return this;
	}

	/**
	 * Sets the range of a ProgressBar to 0...max.
	 *
	 * @param viewId
	 *            The view id.
	 * @param max
	 *            The max value of a ProgressBar.
	 * @return The BaseViewHolder for chaining.
	 */
	public BaseViewHolder setMax(int viewId, int max) {
		ProgressBar view = getView(viewId);
		view.setMax(max);
		return this;
	}

	/**
	 * Sets the rating (the number of stars filled) of a RatingBar.
	 *
	 * @param viewId
	 *            The view id.
	 * @param rating
	 *            The rating.
	 * @return The BaseViewHolder for chaining.
	 */
	public BaseViewHolder setRating(int viewId, float rating) {
		RatingBar view = getView(viewId);
		view.setRating(rating);
		return this;
	}

	/**
	 * Sets the rating (the number of stars filled) and max of a RatingBar.
	 *
	 * @param viewId
	 *            The view id.
	 * @param rating
	 *            The rating.
	 * @param max
	 *            The range of the RatingBar to 0...max.
	 * @return The BaseViewHolder for chaining.
	 */
	public BaseViewHolder setRating(int viewId, float rating, int max) {
		RatingBar view = getView(viewId);
		view.setMax(max);
		view.setRating(rating);
		return this;
	}

	/**
	 * Sets the on click listener of the view.
	 *
	 * @param viewId
	 *            The view id.
	 * @param listener
	 *            The on click listener;
	 * @return The BaseViewHolder for chaining.
	 */
	public BaseViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
		View view = getView(viewId);
		view.setOnClickListener(listener);
		return this;
	}

	public BaseViewHolder setOnClickListener(int viewId, BaseQuickAdapter.OnItemChildClickListener listener) {
		View view = getView(viewId);
		listener.mViewHolder = this;
		view.setOnClickListener(listener);
		return this;
	}

	/**
	 * Sets the on longClick listener of the view.
	 * 
	 * @param viewId
	 * @param listener
	 * @return
	 */
	public BaseViewHolder setOnLongClickListener(int viewId, BaseQuickAdapter.OnItemChildLongClickListener listener) {
		View view = getView(viewId);
		listener.mViewHolder = this;
		view.setOnLongClickListener(listener);
		return this;
	}

	/**
	 * Sets the on touch listener of the view.
	 *
	 * @param viewId
	 *            The view id.
	 * @param listener
	 *            The on touch listener;
	 * @return The BaseViewHolder for chaining.
	 */
	public BaseViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
		View view = getView(viewId);
		view.setOnTouchListener(listener);
		return this;
	}

	/**
	 * Sets the on long click listener of the view.
	 *
	 * @param viewId
	 *            The view id.
	 * @param listener
	 *            The on long click listener;
	 * @return The BaseViewHolder for chaining.
	 */
	public BaseViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
		View view = getView(viewId);
		view.setOnLongClickListener(listener);
		return this;
	}

	/**
	 * Sets the listview or gridview's item click listener of the view
	 *
	 * @param viewId
	 *            The view id.
	 * @param listener
	 *            The item on click listener;
	 * @return The BaseViewHolder for chaining.
	 */
	public BaseViewHolder setOnItemClickListener(int viewId, AdapterView.OnItemClickListener listener) {
		AdapterView view = getView(viewId);
		view.setOnItemClickListener(listener);
		return this;
	}

	/**
	 * Sets the listview or gridview's item long click listener of the view
	 *
	 * @param viewId
	 *            The view id.
	 * @param listener
	 *            The item long click listener;
	 * @return The BaseViewHolder for chaining.
	 */
	public BaseViewHolder setOnItemLongClickListener(int viewId, AdapterView.OnItemLongClickListener listener) {
		AdapterView view = getView(viewId);
		view.setOnItemLongClickListener(listener);
		return this;
	}

	/**
	 * Sets the listview or gridview's item selected click listener of the view
	 *
	 * @param viewId
	 *            The view id.
	 * @param listener
	 *            The item selected click listener;
	 * @return The BaseViewHolder for chaining.
	 */
	public BaseViewHolder setOnItemSelectedClickListener(int viewId, AdapterView.OnItemSelectedListener listener) {
		AdapterView view = getView(viewId);
		view.setOnItemSelectedListener(listener);
		return this;
	}

	/**
	 * Sets the on checked change listener of the view.
	 *
	 * @param viewId
	 *            The view id.
	 * @param listener
	 *            The checked change listener of compound button.
	 * @return The BaseViewHolder for chaining.
	 */
	public BaseViewHolder setOnCheckedChangeListener(int viewId, CompoundButton.OnCheckedChangeListener listener) {
		CompoundButton view = getView(viewId);
		view.setOnCheckedChangeListener(listener);
		return this;
	}

	/**
	 * Sets the tag of the view.
	 *
	 * @param viewId
	 *            The view id.
	 * @param tag
	 *            The tag;
	 * @return The BaseViewHolder for chaining.
	 */
	public BaseViewHolder setTag(int viewId, Object tag) {
		View view = getView(viewId);
		view.setTag(tag);
		return this;
	}

	/**
	 * Sets the tag of the view.
	 *
	 * @param viewId
	 *            The view id.
	 * @param key
	 *            The key of tag;
	 * @param tag
	 *            The tag;
	 * @return The BaseViewHolder for chaining.
	 */
	public BaseViewHolder setTag(int viewId, int key, Object tag) {
		View view = getView(viewId);
		view.setTag(key, tag);
		return this;
	}

	/**
	 * Sets the checked status of a checkable.
	 *
	 * @param viewId
	 *            The view id.
	 * @param checked
	 *            The checked status;
	 * @return The BaseViewHolder for chaining.
	 */
	public BaseViewHolder setChecked(int viewId, boolean checked) {
		View view = getView(viewId);
		// View unable cast to Checkable
		if (view instanceof CompoundButton) {
			((CompoundButton) view).setChecked(checked);
		} else if (view instanceof CheckedTextView) {
			((CheckedTextView) view).setChecked(checked);
		}
		return this;
	}

	/**
	 * Sets the adapter of a adapter view.
	 *
	 * @param viewId
	 *            The view id.
	 * @param adapter
	 *            The adapter;
	 * @return The BaseViewHolder for chaining.
	 */
	public BaseViewHolder setAdapter(int viewId, Adapter adapter) {
		AdapterView view = getView(viewId);
		view.setAdapter(adapter);
		return this;
	}

	@SuppressWarnings("unchecked")
	public <T extends View> T getView(int viewId) {
		View view = views.get(viewId);
		if (view == null) {
			view = convertView.findViewById(viewId);
			views.put(viewId, view);
		}
		return (T) view;
	}

	/**
	 * Retrieves the last converted object on this view.
	 */
	public Object getAssociatedObject() {
		return associatedObject;
	}

	/**
	 * Should be called during convert
	 */
	public void setAssociatedObject(Object associatedObject) {
		this.associatedObject = associatedObject;
	}

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

//	/**
//	 * 填充微博列表图片
//	 *
//	 * @param context
//	 * @param status
//	 * @param position
//	 * @param imageView
//	 * @param imageType
//	 */
//	public static void fillImageList(final Context context, final Status status, final int position, final ImageView imageView, final ImageView imageType) {
//
//		final ArrayList<String> datas = status.bmiddle_pic_urls;
//		ImageLoader.getInstance().displayImage(datas.get(position), imageView, mImageItemOptions, new ImageLoadingListener() {
//			@Override
//			public void onLoadingStarted(String s, View view) {
//				//单张图片的时候，从预设的3种图片尺寸中随机选一种
//				if (datas.size() == 1) {
//					FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imageView.getLayoutParams();
//					switch (status.singleImgSizeType) {
//						case Constants.IMGSIZE_VERTICAL:
//							//竖直方向的长方形尺寸
//							layoutParams.height = (int) context.getResources().getDimension(R.dimen.weibo_status_imagesize_vertical_rectangle_height);
//							layoutParams.width = (int) context.getResources().getDimension(R.dimen.weibo_status_imagesize_vertical_rectangle_width);
//							break;
//						case Constants.IMGSIZE_HORIZONTAL:
//							//水平方向的长方形尺寸
//							layoutParams.height = (int) context.getResources().getDimension(R.dimen.weibo_status_imagesize_horizontal_rectangle_height);
//							layoutParams.width = (int) context.getResources().getDimension(R.dimen.weibo_status_imagesize_horizontal_rectangle_width);
//							break;
//						case Constants.IMGSIZE_SQUARE:
//							//正方形尺寸
//							layoutParams.height = (int) context.getResources().getDimension(R.dimen.weibo_status_imagesize_square_height);
//							layoutParams.width = (int) context.getResources().getDimension(R.dimen.weibo_status_imagesize_square_width);
//							break;
//					}
//				}
//			}
//
//			@Override
//			public void onLoadingFailed(String s, View view, FailReason failReason) {
//
//			}
//
//			@Override
//			public void onLoadingComplete(String s, View view, Bitmap bitmap) {
//				//根据加载完成的BitMap大小，判断是否是长微博图片，设置右下角的图片类型icon
//				int type = returnImageType(context, bitmap);
//				if (type == IMAGE_TYPE_LONG_TEXT) {
//					imageType.setImageResource(R.drawable.timeline_image_longimage);
//				}
//				//根据请求的URL，判断是否是Gif，设置右下角的图片类型icon
//				if (returnImageType(context, datas.get(position)) == IMAGE_TYPE_GIF) {
//					imageType.setImageResource(R.drawable.timeline_image_gif);
//				}
//				//若是长微博
//				if (returnImageType(context, bitmap) == IMAGE_TYPE_LONG_TEXT) {
//					((CropImageView) imageView).setCropType(CropImageView.CropType.CENTER_TOP);
//				}
//				//若只存在单张图片，且图片是长微博，还需要纠正尺寸
//				if (returnImageType(context, bitmap) == IMAGE_TYPE_LONG_TEXT && datas.size() == 1) {
//					FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imageView.getLayoutParams();
//					layoutParams.height = (int) context.getResources().getDimension(R.dimen.home_weiboitem_imagesize_vertical_rectangle_height);
//					layoutParams.width = (int) context.getResources().getDimension(R.dimen.home_weiboitem_imagesize_vertical_rectangle_width);
//					((CropImageView) imageView).setCropType(CropImageView.CropType.CENTER_TOP);
//					status.singleImgSizeType = Constants.IMGSIZE_VERTICAL;
//				}
//			}
//
//			@Override
//			public void onLoadingCancelled(String s, View view) {
//
//			}
//		});
//
//		//设置图片的点击时间
//		imageView.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
////				Intent intent = new Intent(context, ImageDetailsActivity.class);
////				intent.putExtra("imagelist_url", status.origin_pic_urls);
////				intent.putExtra("image_position", position);
////				context.startActivity(intent);
//			}
//		});
//	}

//	public interface ImageLoadingListener{
//		void onLoadingStarted(String s, View view);
//		void onLoadingFailed(String s, View view, FailReason failReason);
//		void onLoadingComplete(String s, View view, Bitmap bitmap);
//		void onLoadingCancelled(String s, View view);
//	}
//
//
//	/**
//	 * 根据下载的图片的大小，返回图片的类型
//	 *
//	 * @param context
//	 * @param bitmap
//	 * @return
//	 */
//	public static int returnImageType(Context context, Bitmap bitmap) {
//		int width = bitmap.getWidth();
//		int height = bitmap.getHeight();
//
//		//长微博尺寸
//		if (height >= width * 3) {
//			return IMAGE_TYPE_LONG_TEXT;
//		}
//
//		return IMAGE_TYPE_WIDTH_PIC;
//	}
//
//	/**
//	 * 根据url链接判断是否为Gif，返回图片类型
//	 *
//	 * @param context
//	 * @param url
//	 * @return
//	 */
//	public static int returnImageType(Context context, String url) {
//		//长微博尺寸
//		if (url.endsWith(".gif")) {
//			return IMAGE_TYPE_GIF;
//		}
//
//		return IMAGE_TYPE_WIDTH_PIC;
//	}
}