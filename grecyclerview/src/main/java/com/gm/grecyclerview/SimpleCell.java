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

package com.gm.grecyclerview;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

public abstract class SimpleCell<T, VH extends SimpleViewHolder> {

  @Deprecated
  public interface OnCellClickListener<T> {
    void onCellClicked(T item);
  }

  @Deprecated
  public interface OnCellLongClickListener<T> {
    void onCellLongClicked(T item);
  }

  public interface OnCellClickListener2<CELL, VH, T> {
    void onCellClicked(CELL cell, VH viewHolder, T item);
  }

  public interface OnCellLongClickListener2<CELL, VH, T> {
    void onCellLongClicked(CELL cell, VH viewHolder, T item);
  }

  private int spanSize = 1;
  private T item;
  private OnCellClickListener onCellClickListener;
  private OnCellClickListener2 onCellClickListener2;
  private OnCellLongClickListener onCellLongClickListener;
  private OnCellLongClickListener2 onCellLongClickListener2;

  public SimpleCell(T item) {
    this.item = item;
  }

  @LayoutRes protected abstract int getLayoutRes();

  @NonNull protected abstract VH onCreateViewHolder(ViewGroup parent, View cellView);

  protected abstract void onBindViewHolder(VH holder, int position, Context context, Object payload);

  protected void onUnbindViewHolder(VH holder) {
  }

  public T getItem() {
    return item;
  }

  public void setItem(T item) {
    this.item = item;
  }

  protected abstract long getItemId();

  public int getSpanSize() {
    return spanSize;
  }

  public void setSpanSize(int spanSize) {
    this.spanSize = spanSize;
  }

  @Deprecated
  public void setOnCellClickListener(OnCellClickListener onCellClickListener) {
    this.onCellClickListener = onCellClickListener;
  }

  @Deprecated
  public void setOnCellLongClickListener(OnCellLongClickListener onCellLongClickListener) {
    this.onCellLongClickListener = onCellLongClickListener;
  }

  @Deprecated
  public OnCellClickListener getOnCellClickListener() {
    return onCellClickListener;
  }

  @Deprecated
  public OnCellLongClickListener getOnCellLongClickListener() {
    return onCellLongClickListener;
  }

  public void setOnCellClickListener2(OnCellClickListener2 onCellClickListener2) {
    this.onCellClickListener2 = onCellClickListener2;
  }

  public void setOnCellLongClickListener2(OnCellLongClickListener2 onCellLongClickListener2) {
    this.onCellLongClickListener2 = onCellLongClickListener2;
  }

  public OnCellClickListener2 getOnCellClickListener2() {
    return onCellClickListener2;
  }

  public OnCellLongClickListener2 getOnCellLongClickListener2() {
    return onCellLongClickListener2;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SimpleCell<?, ?> cell = (SimpleCell<?, ?>) o;

    return getItemId() == cell.getItemId();

  }

  @Override
  public int hashCode() {
    return (int) (getItemId() ^ (getItemId() >>> 32));
  }

}
