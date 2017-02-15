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

package com.gm.grecyclerview.addon;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.gm.grecyclerview.GmRecyclerView;

/**
 * Name       : Gowtham
 * Created on : 15/2/17.
 * Email      : goutham.gm11@gmail.com
 * GitHub     : https://github.com/goutham106
 */
class SwipeToDismissItemCallback extends ItemTouchHelper.Callback {

  private OnItemDismissListener callback;
  private SwipeToDismissOptions options;
  private GmRecyclerView simpleRecyclerView;
  private Object swipeItem;
  private boolean isItemSettled;
  private boolean isItemSwiped;

  SwipeToDismissItemCallback(OnItemDismissListener callback,
                             SwipeToDismissOptions options) {
    this.callback = callback;
    this.options = options;
  }

  @Override
  public boolean isItemViewSwipeEnabled() {
    return true;
  }

  @Override
  public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
    if (simpleRecyclerView == null) {
      simpleRecyclerView = (GmRecyclerView) recyclerView;
    }

    // obtain current item
    swipeItem = simpleRecyclerView.getCell(viewHolder.getAdapterPosition()).getItem();

    // reset
    isItemSettled = false;
    isItemSwiped = false;

    int swipeFlags = 0;

    if (options.canSwipeLeft()) {
      swipeFlags = swipeFlags | ItemTouchHelper.START;
    }
    if (options.canSwipeRight()) {
      swipeFlags = swipeFlags | ItemTouchHelper.END;
    }
    if (options.canSwipeUp()) {
      swipeFlags = swipeFlags | ItemTouchHelper.UP;
    }
    if (options.canSwipeDown()) {
      swipeFlags = swipeFlags | ItemTouchHelper.DOWN;
    }

    return makeMovementFlags(0, swipeFlags);
  }

  @Override
  public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
    isItemSwiped = true;
    callback.onItemDismissed(viewHolder.getAdapterPosition());
  }

  @Override
  public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
    // no-op
    return false;
  }

  @Override
  public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isControlledByUser) {
    super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isControlledByUser);

    if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
      if (options.getSwipeToDismissCallback() != null && !isItemSettled) {
        options.getSwipeToDismissCallback().onCellSwiping(simpleRecyclerView, viewHolder.itemView, swipeItem, viewHolder.getAdapterPosition(), canvas, dX, dY, isControlledByUser);
      }

      if (options.isDefaultFadeOutEffectEnabled()) {
        boolean isHorizontal = dX != 0;
        float offset = isHorizontal ? dX : dY;
        float dimension = isHorizontal ? viewHolder.itemView.getWidth() : viewHolder.itemView.getHeight();
        final float alpha = 1.0f - Math.abs(offset) / dimension;
        viewHolder.itemView.setAlpha(alpha);
      }

      if (options.canSwipeLeft() | options.canSwipeRight()) {
        viewHolder.itemView.setTranslationX(dX);
      } else {
        viewHolder.itemView.setTranslationY(dY);
      }
    }
  }

  @Override
  public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
    super.onSelectedChanged(viewHolder, actionState);
  }

  @Override
  public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
    super.clearView(recyclerView, viewHolder);

    isItemSettled = true;

    int position = viewHolder.getAdapterPosition();

    if (options.getSwipeToDismissCallback() != null) {
      if (isItemSwiped) {
        options.getSwipeToDismissCallback().onCellDismissed(simpleRecyclerView, swipeItem, position);
      } else {
        options.getSwipeToDismissCallback().onCellSettled(simpleRecyclerView, viewHolder.itemView, swipeItem, position);
      }
    }

    if (options.isDefaultFadeOutEffectEnabled()) {
      viewHolder.itemView.setAlpha(1.0f);
    }
  }

}
