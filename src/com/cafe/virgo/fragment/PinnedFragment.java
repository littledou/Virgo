package com.cafe.virgo.fragment;

import java.util.Locale;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.cafe.virgo.BaseFragment;
import com.cafe.virgo.R;
import com.cafe.virgo.widget.PinnedSectionListView;
import com.cafe.virgo.widget.PinnedSectionListView.PinnedSectionListAdapter;

public class PinnedFragment extends BaseFragment{
	private View iView;
	private boolean isFastScroll = false;
	private PinnedSectionListView list;

	@SuppressLint("InflateParams")
	public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle){
		iView = paramLayoutInflater.inflate(R.layout.activity_main, null);
		return iView;
	}
	@SuppressLint("NewApi")
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		list = (PinnedSectionListView) iView.findViewById(R.id.list);
		list.setFastScrollEnabled(isFastScroll);
		if (isFastScroll) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				list.setFastScrollAlwaysVisible(true);
			}
			list.setAdapter(new FastScrollAdapter(mContext, android.R.layout.simple_list_item_1, android.R.id.text1));
		} else {
			list.setAdapter(new SimpleAdapter(mContext, android.R.layout.simple_list_item_1, android.R.id.text1));
		}
	}


	static class SimpleAdapter extends ArrayAdapter<Item> implements PinnedSectionListAdapter {

		private static final int[] COLORS = new int[] {
			R.color.gray_line, R.color.gray_line,
			R.color.gray_line, R.color.gray_line 
		};

		public SimpleAdapter(Context context, int resource, int textViewResourceId) {
			super(context, resource, textViewResourceId);
			generateDataset('A', 'Z', false);
		}

		public void generateDataset(char from, char to, boolean clear) {

			if (clear) clear();

			final int sectionsNumber = to - from + 1;
			prepareSections(sectionsNumber);

			int sectionPosition = 0, listPosition = 0;
			for (char i=0; i<sectionsNumber; i++) {
				Item section = new Item(Item.SECTION, String.valueOf((char)('A' + i)));
				section.sectionPosition = sectionPosition;
				section.listPosition = listPosition++;
				onSectionAdded(section, sectionPosition);
				add(section);

				int itemsNumber = 3;
				for (int j=0;j<itemsNumber;j++) {
					Item item = new Item(Item.ITEM, section.text.toUpperCase(Locale.ENGLISH) + " - " + j);
					item.sectionPosition = sectionPosition;
					item.listPosition = listPosition++;
					add(item);
				}

				sectionPosition++;
			}
		}

		protected void prepareSections(int sectionsNumber) { }
		protected void onSectionAdded(Item section, int sectionPosition) {}

		@Override 
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView view = (TextView) super.getView(position, convertView, parent);
			view.setTextColor(Color.DKGRAY);
			view.setTag("" + position);
			Item item = getItem(position);
			if (item.type == Item.SECTION) {
				//view.setOnClickListener(PinnedSectionListActivity.this);
				view.setBackgroundColor(parent.getResources().getColor(COLORS[item.sectionPosition % COLORS.length]));
			}
			return view;
		}

		@Override public int getViewTypeCount() {
			return 2;
		}

		@Override public int getItemViewType(int position) {
			return getItem(position).type;
		}

		@Override
		public boolean isItemViewTypePinned(int viewType) {
			return viewType == Item.SECTION;
		}
	}

	static class FastScrollAdapter extends SimpleAdapter implements SectionIndexer {

		private Item[] sections;

		public FastScrollAdapter(Context context, int resource, int textViewResourceId) {
			super(context, resource, textViewResourceId);
		}

		@Override protected void prepareSections(int sectionsNumber) {
			sections = new Item[sectionsNumber];
		}

		@Override protected void onSectionAdded(Item section, int sectionPosition) {
			sections[sectionPosition] = section;
		}

		@Override public Item[] getSections() {
			return sections;
		}

		@Override public int getPositionForSection(int section) {
			if (section >= sections.length) {
				section = sections.length - 1;
			}
			return sections[section].listPosition;
		}

		@Override public int getSectionForPosition(int position) {
			if (position >= getCount()) {
				position = getCount() - 1;
			}
			return getItem(position).sectionPosition;
		}

	}

	static class Item {

		public static final int ITEM = 0;
		public static final int SECTION = 1;

		public final int type;
		public final String text;

		public int sectionPosition;
		public int listPosition;

		public Item(int type, String text) {
			this.type = type;
			this.text = text;
		}

		@Override public String toString() {
			return text;
		}

	}

}
