package com.cafe.virgo.fragmentactivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONStringer;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.cafe.virgo.BaseApplication;
import com.cafe.virgo.BaseFragmentActivity;
import com.cafe.virgo.R;
import com.cafe.virgo.corethread.AsyncTaskExecutor;
import com.cafe.virgo.enity.ItemsGetFromDapei;
import com.cafe.virgo.enity.PublishPairs.Items.Mask_spec;
import com.cafe.virgo.enity.PublishPairs.Items.Template_spec;
import com.cafe.virgo.fragment.PinnedFragment;
import com.cafe.virgo.util.BitmapUtils;
import com.cafe.virgo.util.DLogUtils;
import com.cafe.virgo.util.DimensionUtils;
import com.cafe.virgo.util.DisplayUtil;
import com.cafe.virgo.util.HttpUtils;
import com.cafe.virgo.util.MatrixUtils;
import com.cafe.virgo.util.ToastUtils;
import com.cafe.virgo.widget.LightProgressDialog;
import com.cafe.virgo.widget.TouchView;
@SuppressLint("NewApi")
public class MainPageItemActivity extends BaseFragmentActivity{

	private RelativeLayout outer,item_right;
	private View save_share;
	private FrameLayout parent;
	private int screenHeight, width, height, statusBarHeight;
	private AlertDialog progress;

	private int dapeiWidth, dapeiHeight;
	private String templateId = null;
	private Float centerX = 0f, centerY = 0f;
	private float scaleFromOtherPlat = 1f;
	private int toRecover = 0, recovered = 0;
	private static boolean disableFlag = true;// 能否添加图片
	private int selectedNo = 0;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_itemdetail);
		screenHeight = BaseApplication.getInstence().scrn.ScrnH_px;
		statusBarHeight = DimensionUtils.getStatusBarHeight(mContext);
		height = (screenHeight - statusBarHeight-DisplayUtil.dip2px(mContext, 44));
		width = DimensionUtils.getDimensionSizeW(1765);
		width = width*3/4;
		height = height*4/5;
		initView();
		initViewData();
		initTask();
	}
	private void initTask() {
		String url = "http://www.shangjieba.com:8080/cgi/set.load.json?request="+generateResultRequest(5531517)+"&token=shangjieba-8d17a601-ed23-442c-8ce2-c0ef0cc2f20a";
		progress = LightProgressDialog.create(mContext,"正在打开 ...");
		progress.show();
		try {
			AsyncTaskExecutor.executeConcurrently(new ResultTask(String.valueOf(System.currentTimeMillis())), url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void initView() {
		outer = (RelativeLayout) findViewById(R.id.item_left);
		outer.setBackgroundColor(getResources().getColor(R.color.white));	
		outer.setLayoutParams(new LinearLayout.LayoutParams(DimensionUtils.getDimensionSizeW(1766), LinearLayout.LayoutParams.MATCH_PARENT));
		parent = new FrameLayout(this);
		parent.setBackgroundColor(getResources().getColor(R.color.transparent));
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(width,height);
		lp.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
		parent.setLayoutParams(lp);
		outer.addView(parent);

		item_right = (RelativeLayout) findViewById(R.id.item_right);
		item_right.setLayoutParams(new LinearLayout.LayoutParams(DimensionUtils.getDimensionSizeW(282), LinearLayout.LayoutParams.MATCH_PARENT));
		FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
		localFragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
		localFragmentTransaction.replace(R.id.item_right,new PinnedFragment());
		localFragmentTransaction.commit();

		save_share = findViewById(R.id.save_share);
		RelativeLayout.LayoutParams saveParams = (RelativeLayout.LayoutParams) save_share.getLayoutParams();
		saveParams.height = DimensionUtils.getDimensionSizeW(88);
		saveParams.width = DimensionUtils.getDimensionSizeW(397);
		save_share.setLayoutParams(saveParams);

	}
	private void initViewData() {

	}

	/**
	 *打开一个搭配模版
	 */
	private class ResultTask extends AsyncTask<String, Integer, ArrayList<ItemsGetFromDapei>> {

		public ResultTask(String name) {
		}

		@Override
		protected ArrayList<ItemsGetFromDapei> doInBackground(String... params) {
			ArrayList<ItemsGetFromDapei> result = null;
			try {
				result = HttpUtils.getMineDapeiDetail(params[0]);
				DLogUtils.syso("打开搭配模版api---"+params[0]);
				float minX = Float.parseFloat(result.get(0).getX());
				float minY = Float.parseFloat(result.get(0).getY());
				float maxX = Float.parseFloat(result.get(0).getX())+ Float.parseFloat(result.get(0).getW());
				float maxY = Float.parseFloat(result.get(0).getY())+ Float.parseFloat(result.get(0).getH());
				for (int i = 1; i < result.size(); i++) {
					if (Float.parseFloat(result.get(i).getX()) < minX) {
						minX = Float.parseFloat(result.get(i).getX());
					}
					if (Float.parseFloat(result.get(i).getY()) < minY) {
						minY = Float.parseFloat(result.get(i).getY());
					}
					if (Float.parseFloat(result.get(i).getX())
							+ Float.parseFloat(result.get(i).getW()) > maxX) {
						maxX = Float.parseFloat(result.get(i).getX())
								+ Float.parseFloat(result.get(i).getW());
					}
					if (Float.parseFloat(result.get(i).getY())
							+ Float.parseFloat(result.get(i).getH()) > maxY) {
						maxY = Float.parseFloat(result.get(i).getY())
								+ Float.parseFloat(result.get(i).getH());
					}
				}
				dapeiWidth = (int) (maxX - minX);
				dapeiHeight = (int) (maxY - minY);
				centerX = minX + (maxX - minX) / 2;
				centerY = minY + (maxY - minY) / 2;

				templateId = HttpUtils.getCId();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		@Override
		protected void onPostExecute(ArrayList<ItemsGetFromDapei> result) {
			if(mContext==null){
				return;
			}
			try {
				if ((float) dapeiWidth / (float) width > (float) dapeiHeight/ (float) height) {
					scaleFromOtherPlat = (float) width / (float) dapeiWidth* 0.9f;
				} else {
					scaleFromOtherPlat = (float) height / (float) dapeiHeight* 0.8f;
				}
				recoverDapei(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**将搭配铺出来
	 */
	private void recoverDapei(ArrayList<ItemsGetFromDapei> result) {
		try {
			recovered = 0;
			toRecover = result.size();
			for (int i = 0; i < toRecover; i++) {
				if (result.get(i).getMask_spec().length() == 0 && result.get(i).getTemplate_spec().length() == 0) {
					//TODO 未裁剪图片还原
					recoverTask(result.get(i), centerX, centerY);
				} else {
					//已裁剪图片还原
					recoverCropTask(result.get(i), centerX, centerY);
				}
			}

			for (int i = 0; i < toRecover; i++) {
				if (result.get(i).getMask_spec().length() == 0 && result.get(i).getTemplate_spec().length() == 0) {
					AsyncTaskExecutor.executeConcurrently(new RecoverTask(result.get(i), i));
				} else {
					AsyncTaskExecutor.executeConcurrently(new RecoverCropTask(result.get(i), i));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 还原图片
	private void recoverTask(ItemsGetFromDapei result, float centerX,
			float centerY) {

		TouchView itm = new TouchView(mContext);
		itm.setClickable(true);
		itm.setThingid(result.getThing_id());
		itm.setThingurl(result.getImg_png());
		itm.setObjectid(result.getObject_id());
		itm.setScaleType(ScaleType.FIT_CENTER);
		FrameLayout.LayoutParams lp;
		if (result.getOw().length()!=0 && result.getOh().length() != 0) {
			lp = new FrameLayout.LayoutParams(
					(int) Float.parseFloat(result.getOw()),
					(int) Float.parseFloat(result.getOh()));
		}
		else {
			lp = new FrameLayout.LayoutParams(
					(int) Float.parseFloat(result.getW()),
					(int) Float.parseFloat(result.getH()));
		}
		lp.gravity = Gravity.CENTER;

		itm.setLayoutParams(lp);

		if (dapeiWidth == 0 || dapeiHeight == 0) {
			itm.setX(Float.parseFloat(result.getX())
					+ Float.parseFloat(result.getW()) / 2 - centerX);
			itm.setY(Float.parseFloat(result.getY())
					+ Float.parseFloat(result.getH()) / 2 - centerY);
			itm.setScaleX(Float.parseFloat(result.getW())
					/ Float.parseFloat(result.getOw()));
			itm.setScaleY(Float.parseFloat(result.getH())
					/ Float.parseFloat(result.getOh()));
		} else {
			itm.setX((Float.parseFloat(result.getX())
					+ Float.parseFloat(result.getW()) / 2 - centerX)
					* scaleFromOtherPlat);
			itm.setY((Float.parseFloat(result.getY())
					+ Float.parseFloat(result.getH()) / 2 - centerY)
					* scaleFromOtherPlat);
			if (result.getOw().length()!=0 && result.getOh().length() != 0) {
				itm.setScaleX(Float.parseFloat(result.getW())
						/ Float.parseFloat(result.getOw()) * scaleFromOtherPlat);
				itm.setScaleY(Float.parseFloat(result.getH())
						/ Float.parseFloat(result.getOh()) * scaleFromOtherPlat);
			}
			else {
				itm.setScaleX(Float.parseFloat(result.getW())
						/ Float.parseFloat(result.getW()) * scaleFromOtherPlat);
				itm.setScaleY(Float.parseFloat(result.getH())
						/ Float.parseFloat(result.getH()) * scaleFromOtherPlat);
			}

		}

		if (result.getTransform()[2] != 0 || result.getTransform()[2] != 0) {
			double tan = result.getTransform()[2] / result.getTransform()[0];
			double angle = Math.toDegrees(Math.atan(tan));
			itm.setRotation((float) angle);
		}

		if (disableFlag) {
			try {
				parent.addView(itm);
				selectedNo = parent.getChildCount() - 1;
				parent.invalidate();
				outer.invalidate();
				logHeap();
			} catch (Exception e) {
				e.printStackTrace();
				e.printStackTrace();
			}
		} else {
			ToastUtils.showShortToast("系统内存不足，不能显示更多单品");
		}
	}

	private void recoverCropTask(ItemsGetFromDapei result, float centerX,
			float centerY) {
		TouchView itm;
		try {
			itm = new TouchView(mContext);
			itm.setClickable(true);
			itm.setThingid(result.getThing_id());
			itm.setThingurl(result.getImg_png());
			itm.setObjectid(result.getObject_id());
			itm.setScaleType(ScaleType.FIT_CENTER);
			FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
					(int) Float.parseFloat(result.getSpec_w()),
					(int) Float.parseFloat(result.getSpec_h()));
			lp.gravity = Gravity.CENTER;

			itm.setLayoutParams(lp);

			if (dapeiWidth == 0 || dapeiHeight == 0) {
				itm.setX(Float.parseFloat(result.getX())
						+ Float.parseFloat(result.getW()) / 2 - centerX);
				itm.setY(Float.parseFloat(result.getY())
						+ Float.parseFloat(result.getH()) / 2 - centerY);
				itm.setScaleX(Float.parseFloat(result.getW())
						/ Float.parseFloat(result.getSpec_w()));
				itm.setScaleY(Float.parseFloat(result.getH())
						/ Float.parseFloat(result.getSpec_h()));
			} else {
				//根据屏幕大小坐标适应
				itm.setX((Float.parseFloat(result.getX())
						+ Float.parseFloat(result.getW()) / 2 - centerX)
						* scaleFromOtherPlat);
				itm.setY((Float.parseFloat(result.getY())
						+ Float.parseFloat(result.getH()) / 2 - centerY)
						* scaleFromOtherPlat);
				//根据屏幕大小进行缩放
				itm.setScaleX((Float.parseFloat(result.getW()) / Float
						.parseFloat(result.getSpec_w())) * scaleFromOtherPlat);
				itm.setScaleY((Float.parseFloat(result.getH()) / Float
						.parseFloat(result.getSpec_h())) * scaleFromOtherPlat);
			}

			if (result.getTransform()[2] != 0 || result.getTransform()[2] != 0) {
				double tan = result.getTransform()[2]
						/ result.getTransform()[0];
				double angle = Math.toDegrees(Math.atan(tan));
				itm.setRotation((float) angle);
			}

			String mask_spec = result.getMask_spec();
			String[] eachCoord = mask_spec.split(" ");
			ArrayList<Mask_spec> listCropperParam = new ArrayList<Mask_spec>();
			for (int i = 0; i < eachCoord.length / 2; i++) {
				Mask_spec cropperParam = new Mask_spec();
				cropperParam.setX(Float.parseFloat(eachCoord[2 * i]));
				cropperParam.setY(Float.parseFloat(eachCoord[2 * i + 1]));
				listCropperParam.add(cropperParam);
			}
			itm.setListMask_spec(listCropperParam);

			String templateSpecString = result.getTemplate_spec();
			if (templateSpecString.length()!= 0 && templateSpecString != null) {
				String[] eachTemplateSpec = templateSpecString.split(" ");
				Template_spec template_spec = new Template_spec();
				template_spec.setTemplate_id(eachTemplateSpec[0]);
				template_spec.setX(Float.parseFloat(eachTemplateSpec[1]));
				template_spec.setY(Float.parseFloat(eachTemplateSpec[2]));
				template_spec.setScale(Float.parseFloat(eachTemplateSpec[3]));
				itm.setTemplate_spec(template_spec);
			}

			if (disableFlag) {
				try {
					parent.addView(itm);
					selectedNo = parent.getChildCount() - 1;
					parent.invalidate();
					outer.invalidate();
					logHeap();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				ToastUtils.showShortToast("系统内存不足，不能显示更多单品");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private class RecoverTask extends AsyncTask<String, Integer, Bitmap> {

		ItemsGetFromDapei result;
		int i;

		public RecoverTask(ItemsGetFromDapei result, int i) {
			this.result = result;
			this.i = i;
		}

		protected Bitmap doInBackground(String... s) {
			Drawable drw = BitmapUtils.loadImageFromUrl(result.getImg_png());
			Bitmap bmp = null;
			if (drw != null) {
				try {
					bmp = (((BitmapDrawable) drw).getBitmap());
					bmp = Bitmap.createScaledBitmap(bmp,
							(int) (bmp.getWidth() * 1.00f),
							(int) (bmp.getHeight() * 1.00f), true);
				} catch (Exception e) {
					e.printStackTrace();
					e.printStackTrace();
				}
			}

			return bmp;
		}

		@Override
		protected void onPostExecute(Bitmap bmp) {
			try {
				if (result.getTransform()[0] == 0
						&& result.getTransform()[1] == 0
						&& result.getTransform()[2] == 0
						&& result.getTransform()[3] == 0) {
					try {
						((TouchView) parent.getChildAt(i)).setImageBitmap(bmp);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (result.getTransform()[0] + result.getTransform()[3] < 0.01f
						&& result.getTransform()[1] - result.getTransform()[2] < 0.01f) {
					Bitmap bitmap = MatrixUtils.getTextureFromBitmapResource(mContext,
							bmp, ((TouchView) parent.getChildAt(i)).getImageMatrix());

					((TouchView) parent.getChildAt(i)).setIsFlip(true);
					((TouchView) parent.getChildAt(i)).setImageBitmap(bitmap);
				} else {
					((TouchView) parent.getChildAt(i)).setImageBitmap(bmp);
				}
				((TouchView) parent.getChildAt(i)).invalidate();

				recovered++;
				if (recovered >= toRecover ) {
					if(mContext==null){
						return;
					}
					progress.dismiss();
					DLogUtils.syso("selectedNo---"+selectedNo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private class RecoverCropTask extends AsyncTask<String, Integer, Bitmap> {

		ItemsGetFromDapei result;
		int i;

		public RecoverCropTask(ItemsGetFromDapei result, int i) {
			this.result = result;
			this.i = i;
		}

		protected Bitmap doInBackground(String... s) {
			Drawable drw = null;
			try {
				drw = BitmapUtils.loadImageFromUrl(result.getSpec_url());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			Bitmap bmp = null;
			if (drw != null) {
				try {
					bmp = (((BitmapDrawable) drw).getBitmap());
					bmp = Bitmap.createScaledBitmap(bmp,
							(int) (bmp.getWidth() * 1.00f),
							(int) (bmp.getHeight() * 1.00f), true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return bmp;
		}

		@Override
		protected void onPostExecute(Bitmap bmp) {
			try {
				if (result.getTransform()[0] == 0
						&& result.getTransform()[1] == 0
						&& result.getTransform()[2] == 0
						&& result.getTransform()[3] == 0) {
					((TouchView) parent.getChildAt(i)).setImageBitmap(bmp);
				} else if (result.getTransform()[0] + result.getTransform()[3] < 0.01f
						&& result.getTransform()[1] - result.getTransform()[2] < 0.01f) {
					Bitmap bitmap = MatrixUtils.getTextureFromBitmapResource(mContext,
							bmp, ((TouchView) parent.getChildAt(i)).getImageMatrix());

					((TouchView) parent.getChildAt(i)).setIsFlip(true);
					((TouchView) parent.getChildAt(i)).setImageBitmap(bitmap);
				} else {
					((TouchView) parent.getChildAt(i)).setImageBitmap(bmp);
				}
				((TouchView) parent.getChildAt(i)).invalidate();

				recovered++;
				if (recovered >= toRecover ) {
					if(mContext==null){
						return;
					}
					DLogUtils.syso("selectedNo---"+selectedNo);
					progress.dismiss();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	// 图像点击监听
	ImageView.OnClickListener imageOnClickListener = new ImageView.OnClickListener() {

		@SuppressWarnings("deprecation")
		@Override
		public void onClick(View v) {
			try {
				for (int i = 0; i < parent.getChildCount(); i++) {
					if (v.equals(parent.getChildAt(i))) {
						selectedNo = i;
						((TouchView) parent.getChildAt(i)).setMarked(true);
						parent.getChildAt(i).invalidate();
					} else {
						((TouchView) parent.getChildAt(i)).setMarked(false);
						parent.getChildAt(i).invalidate();
					}
				}
				parent.invalidate();
				outer.invalidate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	public static void logHeap() {

		try {
			Double allocated = new Double(Debug.getNativeHeapAllocatedSize());// new
			// Double((1048576));
			Double available = new Double(Debug.getNativeHeapSize());// 1048576.0;
			Double free = new Double(Debug.getNativeHeapFreeSize());// 1048576.0;

			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);
			df.setMinimumFractionDigits(2);

			Log.i("gettings", "debug. =================================");
			Log.i("gettings",
					"debug.heap native: allocated " + df.format(allocated)
					+ "MB of " + df.format(available) + "MB ("
					+ df.format(free) + "MB free)");
			Log.i("gettings",
					"debug.memory: allocated: "
							+ df.format(new Double(Runtime.getRuntime()
									.totalMemory() / 1048576))
									+ "MB of "
									+ df.format(new Double(
											Runtime.getRuntime().maxMemory() / 1048576))
											+ "MB ("
											+ df.format(new Double(Runtime.getRuntime()
													.freeMemory() / 1048576)) + "MB free)");

			if ((Runtime.getRuntime().maxMemory() / 1024) - allocated / 1024 <= (6 * 1024)) {
				Log.i("gettings",
						"debug. for shut down=================================");
				disableFlag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private String generateResultRequest(int did) {
		JSONStringer js = new JSONStringer();
		try {
			js.object();
			js.key("id").value(did);
			js.endObject();
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		String strUT = "";
		try {
			strUT = URLEncoder.encode(js.toString(), "gb2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return strUT;
	}
}
