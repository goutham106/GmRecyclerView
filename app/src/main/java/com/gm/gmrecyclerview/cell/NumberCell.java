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

package com.gm.gmrecyclerview.cell;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gm.gmrecyclerview.R;
import com.gm.grecyclerview.SimpleCell;
import com.gm.grecyclerview.SimpleViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NumberCell extends SimpleCell<Integer, NumberCell.ViewHolder> {

  public NumberCell(Integer number) {
    super(number);
  }

  @Override
  protected int getLayoutRes() {
    return R.layout.cell_book_num;
  }

  @NonNull
  @Override
  protected ViewHolder onCreateViewHolder(ViewGroup parent, View cellView) {
    return new ViewHolder(cellView);
  }

  @Override
  protected void onBindViewHolder(ViewHolder holder, int position, Context context, Object payload) {
    holder.textView.setText(String.valueOf(getItem()));
  }

  @Override
  protected long getItemId() {
    return getItem();
  }

  static class ViewHolder extends SimpleViewHolder {
    @BindView(R.id.textView)
    TextView textView;

    ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

}
