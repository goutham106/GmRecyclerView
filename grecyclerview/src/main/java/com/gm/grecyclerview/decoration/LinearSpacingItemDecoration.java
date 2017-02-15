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

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Name       : Gowtham
 * Created on : 15/2/17.
 * Email      : goutham.gm11@gmail.com
 * GitHub     : https://github.com/goutham106
 */
public class LinearSpacingItemDecoration extends RecyclerView.ItemDecoration {

  private int spacing;
  private boolean includeEdge;
  private boolean isVertical;

  private LinearSpacingItemDecoration(Builder builder) {
    spacing = builder.spacing;
    includeEdge = builder.includeEdge;
    isVertical = builder.orientation == LinearLayoutManager.VERTICAL;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  @Override
  public void getItemOffsets(Rect outRect, View view,
                             RecyclerView parent, RecyclerView.State state) {
    int position = parent.getChildAdapterPosition(view);
    boolean isFirstCell = position == 0;
    boolean isLastCell = position == parent.getAdapter().getItemCount() - 1;

    if (isFirstCell) {
      if (isVertical) {
        outRect.top = includeEdge ? spacing : 0;
        outRect.bottom = isLastCell ? (includeEdge ? spacing : 0) : 0;
      } else {
        outRect.left = includeEdge ? spacing : 0;
        outRect.right = spacing / 2;
      }
    } else if (isLastCell) {
      if (isVertical) {
        outRect.top = spacing;
        outRect.bottom = includeEdge ? spacing : 0;
      } else {
        outRect.left = spacing / 2;
        outRect.right = includeEdge ? spacing : 0;
      }
    } else {
      if (isVertical) {
        outRect.top = spacing;
        outRect.bottom = 0;
      } else {
        outRect.left = spacing / 2;
        outRect.right = spacing / 2;
      }
    }
  }

  public static final class Builder {
    private int spacing;
    private boolean includeEdge;
    private int orientation;

    private Builder() {
    }

    public Builder spacing(int val) {
      spacing = val;
      return this;
    }

    public Builder includeEdge(boolean includeEdge) {
      this.includeEdge = includeEdge;
      return this;
    }

    public Builder orientation(int orientation) {
      this.orientation = orientation;
      return this;
    }

    public LinearSpacingItemDecoration build() {
      return new LinearSpacingItemDecoration(this);
    }
  }

}