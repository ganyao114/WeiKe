package com.just.weike.ui.adapter;

import java.io.File;
import java.util.List;

import com.just.weike.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FilelistAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	// private Bitmap mIcon1;
	private Bitmap mIcon2;
	private Bitmap mIcon3;
	//private Bitmap mIcon4;
	private List<String> items;
	private List<String> paths;
	@SuppressWarnings("unused")
	private Context mContext;

	public FilelistAdapter(Context context, List<String> it, List<String> pa) {
		mContext = context;
		inflater = LayoutInflater.from(context);
		items = it;
		paths = pa;
		// mIcon1 = BitmapFactory.decodeResource(context.getResources(),
		// R.drawable.file);
		mIcon2 = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.shortcut_light);
		mIcon3 = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.app_icon);
		//mIcon4 = BitmapFactory.decodeResource(context.getResources(),
		//		iconselect(getMIMEType(f)));
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.filelistlayout, null);
			holder = new ViewHolder();
			holder.text = (TextView) convertView.findViewById(R.id.text);
			holder.icon = (ImageView) convertView.findViewById(R.id.icon);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		File f = new File(paths.get(position).toString());
		if (items.get(position).toString().equals("back")) {
			holder.text.setText("Back to ..");
			holder.icon.setImageBitmap(mIcon2);
		} else {
			holder.text.setText(f.getName());
			if (f.isDirectory()) {
				holder.icon.setImageBitmap(mIcon3);
			} else {
				holder.icon.setImageResource(iconselect(getMIMEType(f)));
				//holder.icon.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),
				//		iconselect(getMIMEType(f))));
			}
		}
		return convertView;

	}
	private int iconselect(String type) {
		int icon = 0;
		switch (type) {
		case "audio/*":
			icon = R.drawable.ic_em_music;
			break;
		case "video/*":
			icon = R.drawable.ic_em_video;
			break;
		case "image/*":
			icon = R.drawable.ic_em_image;
			break;
		case "text/*":
			icon = R.drawable.copy;
			break;
		case "application/vnd.android.package-archive":
			icon = R.drawable.ic_launcher;
			break;
		case "application/*":
			icon = R.drawable.ic_fso_type_compress;
			break;
		default:
			icon = R.drawable.ic_em_document;
			break;
		}
		return icon;
	}
	@SuppressLint("DefaultLocale") private String getMIMEType(File f) {
		String filetype = "";
		String fName = f.getName();
		String end = fName
				.substring(fName.lastIndexOf(".") + 1, fName.length())
				.toLowerCase();
		if (end.equals("m4a") || end.equals("mp3") || end.equals("mid")
				|| end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
			filetype = "audio/*";
		} else if (end.equals("3gp") || end.equals("mp4") || end.equals("rmvb")
				|| end.equals("rm") || end.equals("avi") || end.equals("mkv")) {
			filetype = "video/*";
		} else if (end.equals("jpg") || end.equals("gif") || end.equals("png")
				|| end.equals("jpeg") || end.equals("bmp")) {
			filetype = "image/*";
		} else if (end.equals("txt") || end.equals("umf") || end.equals("pdf")
				|| end.equals("doc") || end.equals("xml")) {
			filetype = "text/*";

		} else if (end.equals("apk")) {
			filetype = "application/vnd.android.package-archive";
		}else if (end.equals("zip")||end.equals("rar")||end.equals("7z")||end.equals("tar")) {
			filetype = "application/*";
		} else {
			filetype = "*/*";
		}

		return filetype;
	}

	private class ViewHolder {
		TextView text;
		ImageView icon;
	}

}
