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

package com.gm.gmrecyclerview;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.gm.gmrecyclerview.cell.BookCell;
import com.gm.gmrecyclerview.model.Book;
import com.gm.gmrecyclerview.util.DataUtils;
import com.gm.gmrecyclerview.util.ToastUtils;
import com.gm.grecyclerview.GmRecyclerView;
import com.gm.grecyclerview.OnLoadMoreListener;
import com.gm.grecyclerview.SimpleCell;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

public class AutoLoadMoreActivity extends BaseActivity {

  @BindView(R.id.recyclerView)
  GmRecyclerView recyclerView;
  @BindView(R.id.loadMoreToTopCheckbox)
  CheckBox loadMoreToTopCheckbox;
  @BindView(R.id.thresholdTextView)
  TextView thresholdTextView;
  @BindView(R.id.thresholdSeekBar)
  SeekBar thresholdSeekBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_auto_load_more);
    ButterKnife.bind(this);
    init();
    bindBooks(DataUtils.getBooks());
  }

  @OnCheckedChanged(R.id.loadMoreToTopCheckbox)
  void onCheckboxChanged(CompoundButton button, boolean isChecked) {
    recyclerView.setLoadMoreToTop(isChecked);
  }

  private String formatThresholdMsg(int threshold) {
    return String.format(getString(R.string.auto_load_more_threshold), threshold);
  }

  private void init() {
    thresholdTextView.setText(formatThresholdMsg(0));
    thresholdSeekBar.setMax(10);
    thresholdSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        recyclerView.setAutoLoadMoreThreshold(progress);
        thresholdTextView.setText(formatThresholdMsg(progress));
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });

    recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
      @Override
      public void onLoadMore(GmRecyclerView simpleRecyclerView) {
        loadBooks();
      }
    });
  }

  private void loadBooks() {
    recyclerView.setLoadingMore(true);
    DataUtils.getBooksAsync(this, new DataUtils.DataCallback() {
      @Override
      public void onSuccess(List<Book> books) {
        bindBooks(books);
        ToastUtils.show(AutoLoadMoreActivity.this.getApplicationContext(), "Load more " + books.size() + " books.");
        recyclerView.setLoadingMore(false);
      }
    });
  }

  private void bindBooks(List<Book> books) {
    List<SimpleCell> cells = new ArrayList<>();

    for (Book book : books) {
      BookCell cell = new BookCell(book);
      cells.add(cell);
    }

    if (recyclerView.isLoadMoreToTop()) {
      recyclerView.addCells(0, cells);
    } else {
      recyclerView.addCells(cells);
    }
  }

}
