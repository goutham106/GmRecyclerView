/*
 * Copyright (c) 2017 Gowtham Parimalazhagan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gm.grecyclerview.decoration;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gm.grecyclerview.GmRecyclerView;

import static android.support.v7.widget.RecyclerView.NO_POSITION;

/**
 * Name       : Gowtham
 * Created on : 15/2/17.
 * Email      : goutham.gm11@gmail.com
 * GitHub     : https://github.com/goutham106
 */
public class SectionHeaderItemDecoration extends RecyclerView.ItemDecoration {

  private SectionHeaderProvider provider;
  private GmRecyclerView simpleRecyclerView;
  private LinearLayoutManager layoutManager;
  private int sectionHeight;
  private boolean isHeaderOverlapped;
  private int firstHeaderTop;
  private int secondHeaderTop;
  private boolean isClipToPadding;

  public SectionHeaderItemDecoration(SectionHeaderProvider provider) {
    this.provider = provider;
  }

  @SuppressLint("NewApi")
  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    // init
    if (simpleRecyclerView == null) {
      simpleRecyclerView = ((GmRecyclerView) parent);
    }
    if (layoutManager == null) {
      layoutManager = (LinearLayoutManager) parent.getLayoutManager();
    }
    isClipToPadding = parent.getClipToPadding();

    int position = parent.getChildAdapterPosition(view);

    if (sectionHeight == 0) {
      View sectionHeader = getAndMeasureSectionHeader(parent, position);
      sectionHeight = sectionHeader.getMeasuredHeight();
    }

    if (!isSameSection(position)) {
      outRect.top = sectionHeight + provider.getSectionHeaderMarginTop(getItem(position), position);
    } else {
      outRect.top = 0;
    }
  }

  // draw section header
  @SuppressLint("NewApi")
  @Override
  public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
    int topPadding = isClipToPadding ? parent.getPaddingTop() : 0;
    int left = parent.getPaddingLeft();
    int right = parent.getWidth() - parent.getPaddingRight();

    boolean isFirst = false;
    for (int i = 0; i < parent.getChildCount(); i++) {
      View view = parent.getChildAt(i);
      int position = parent.getChildAdapterPosition(view);
      if (position != NO_POSITION && !isSameSection(position)) {
        View sectionHeader = getAndMeasureSectionHeader(parent, position);
        int top = view.getTop() - sectionHeight;
        int bottom = view.getTop();
        boolean isHeaderExit = top <= topPadding;

        if (!isFirst) {
          firstHeaderTop = top;
        }
        if (!isFirst && position != 0) {
          secondHeaderTop = top;
          if (isHeaderExit) {
            isHeaderOverlapped = false;
          } else {
            isHeaderOverlapped = secondHeaderTop <= sectionHeight + topPadding;
          }
        }
        isFirst = true;

        sectionHeader.layout(left, top, right, bottom);
        canvas.save();
        if (isClipToPadding && isHeaderExit) {
          canvas.clipRect(left, topPadding, right, bottom);
        }
        canvas.translate(left, top);
        sectionHeader.draw(canvas);
        canvas.restore();
      }
    }
  }

  // draw sticky section header
  @SuppressLint("NewApi")
  @Override
  public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
    if (!provider.isSticky()) {
      return;
    }

    int position = layoutManager.findFirstVisibleItemPosition();

    if (position == NO_POSITION) {
      return;
    }

    int topPadding = isClipToPadding ? parent.getPaddingTop() : 0;
    int left = parent.getPaddingLeft();
    int right = parent.getWidth() - parent.getPaddingRight();
    int top = topPadding;
    int bottom = top + sectionHeight;

    // if android:isClipToPadding="false", first header can be scroll up till reaching top.
    if (!isClipToPadding && position == 0) {
      top = firstHeaderTop > 0 ? firstHeaderTop : 0;
      bottom = top + sectionHeight;
    }

    if (isHeaderOverlapped) {
      top = top - topPadding - (sectionHeight - secondHeaderTop);
      bottom = top + sectionHeight;
    }

    boolean isHeaderExit = top <= topPadding;

    if (isHeaderExit) {
      isHeaderOverlapped = false;
    }

    View sectionHeader = getAndMeasureSectionHeader(parent, position);
    sectionHeader.layout(left, top, right, bottom);
    canvas.save();
    if (isClipToPadding && isHeaderExit) {
      canvas.clipRect(left, topPadding, right, bottom);
    }
    canvas.translate(left, top);
    sectionHeader.draw(canvas);
    canvas.restore();
  }

  private View getAndMeasureSectionHeader(RecyclerView parent, int position) {
    View sectionHeader = provider.getSectionHeaderView(getItem(position), position);
    int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(), View.MeasureSpec.EXACTLY);
    int heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(), View.MeasureSpec.AT_MOST);
    sectionHeader.measure(widthSpec, heightSpec);
    return sectionHeader;
  }

  private boolean isSameSection(int position) {
    if (position == 0) {
      return false;
    }

    return provider.isSameSection(getItem(position), getItem(position - 1));
  }

  private Object getItem(int position) {
    return simpleRecyclerView.getCell(position).getItem();
  }

}
