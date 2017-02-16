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
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gm.gmrecyclerview.R;
import com.gm.gmrecyclerview.model.Book;
import com.gm.grecyclerview.SimpleCell;
import com.gm.grecyclerview.SimpleViewHolder;
import com.gm.grecyclerview.Updatable;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookCell extends SimpleCell<Book, BookCell.ViewHolder>
  implements Updatable<Book> {

  private static final String KEY_TITLE = "KEY_TITLE";
  private boolean showHandle;

  public BookCell(Book item) {
    super(item);
  }

  @Override
  protected int getLayoutRes() {
    return R.layout.cell_book;
  }

  @NonNull
  @Override
  protected ViewHolder onCreateViewHolder(ViewGroup parent, View cellView) {
    return new ViewHolder(cellView);
  }

  @Override
  protected void onBindViewHolder(ViewHolder holder, int position, Context context, Object payload) {
    if (payload != null) {
      // payload from updateCell()
      if (payload instanceof Book) {
        holder.textView.setText(((Book) payload).getTitle());
      }
      // payloads from updateCells()
      if (payload instanceof ArrayList) {
        List<Book> payloads = ((ArrayList<Book>) payload);
        holder.textView.setText(payloads.get(position).getTitle());
      }
      // payload from addOrUpdate()
      if (payload instanceof Bundle) {
        Bundle bundle = ((Bundle) payload);
        for (String key : bundle.keySet()) {
          if (KEY_TITLE.equals(key)) {
            holder.textView.setText(bundle.getString(key));
          }
        }
      }
      return;
    }

    holder.textView.setText(getItem().getTitle());

    if (showHandle) {
      holder.dragHandle.setVisibility(View.VISIBLE);
    } else {
      holder.dragHandle.setVisibility(View.GONE);
    }
  }

  // Optional
  @Override
  protected void onUnbindViewHolder(ViewHolder holder) {
    // do your cleaning jobs here when the item view is recycled.
  }

  @Override
  protected long getItemId() {
    return getItem().getId();
  }

  public void setShowHandle(boolean showHandle) {
    this.showHandle = showHandle;
  }

  /**
   * If the titles of books are same, no need to update the cell, onBindViewHolder() will not be called.
   */
  @Override
  public boolean areContentsTheSame(Book newItem) {
    return getItem().getTitle().equals(newItem.getTitle());
  }

  /**
   * If getItem() is the same as newItem (i.e. their return value of getItemId() are the same)
   * and areContentsTheSame()  return false, then the cell need to be updated,
   * onBindViewHolder() will be called with this payload object.
   */
  @Override
  public Object getChangePayload(Book newItem) {
    Bundle bundle = new Bundle();
    bundle.putString(KEY_TITLE, newItem.getTitle());
    return bundle;
  }

  public static class ViewHolder extends SimpleViewHolder {
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.dragHandle)
    ImageView dragHandle;

    ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

}
