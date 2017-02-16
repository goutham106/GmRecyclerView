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
import android.view.View;

import com.gm.gmrecyclerview.cell.BookCell;
import com.gm.gmrecyclerview.cell.NumberCell;
import com.gm.gmrecyclerview.model.Book;
import com.gm.gmrecyclerview.util.DataUtils;
import com.gm.gmrecyclerview.util.ToastUtils;
import com.gm.grecyclerview.GmRecyclerView;
import com.gm.grecyclerview.SimpleCell;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BasicUsageActivity extends BaseActivity {

  @BindView(R.id.linearVerRecyclerView)
  GmRecyclerView linearVerRecyclerView;
  @BindView(R.id.linearHorRecyclerView)
  GmRecyclerView linearHorRecyclerView;
  @BindView(R.id.gridRecyclerView)
  GmRecyclerView gridRecyclerView;
  @BindView(R.id.gridSequenceRecyclerView)
  GmRecyclerView gridSequenceRecyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_basic_usage);
    ButterKnife.bind(this);
    bindBooks(linearVerRecyclerView);
    bindBooks(gridRecyclerView);
    bindBooks(gridSequenceRecyclerView);
    bindNumbers(linearHorRecyclerView);
  }

  @OnClick(R.id.linearVerRadioButton)
  void showLinearVerRecyclerView() {
    linearVerRecyclerView.setVisibility(View.VISIBLE);
    linearHorRecyclerView.setVisibility(View.GONE);
    gridRecyclerView.setVisibility(View.GONE);
    gridSequenceRecyclerView.setVisibility(View.GONE);
  }

  @OnClick(R.id.linearHorRadioButton)
  void showLinearHorRecyclerView() {
    linearVerRecyclerView.setVisibility(View.GONE);
    linearHorRecyclerView.setVisibility(View.VISIBLE);
    gridRecyclerView.setVisibility(View.GONE);
    gridSequenceRecyclerView.setVisibility(View.GONE);
  }

  @OnClick(R.id.gridRadioButton)
  void showGridRecyclerView() {
    linearVerRecyclerView.setVisibility(View.GONE);
    linearHorRecyclerView.setVisibility(View.GONE);
    gridRecyclerView.setVisibility(View.VISIBLE);
    gridSequenceRecyclerView.setVisibility(View.GONE);
  }

  @OnClick(R.id.gridSequenceRadioButton)
  void showGridSequenceRecyclerView() {
    linearVerRecyclerView.setVisibility(View.GONE);
    linearHorRecyclerView.setVisibility(View.GONE);
    gridRecyclerView.setVisibility(View.GONE);
    gridSequenceRecyclerView.setVisibility(View.VISIBLE);
  }

  private void bindBooks(GmRecyclerView simpleRecyclerView) {
    List<Book> books = DataUtils.getBooks();
    List<BookCell> cells = new ArrayList<>();

    for (Book book : books) {
      BookCell cell = new BookCell(book);
      // There are two default cell listeners: OnCellClickListener<CELL, VH, T> and OnCellLongClickListener<CELL, VH, T>
      cell.setOnCellClickListener2(new SimpleCell.OnCellClickListener2<BookCell, BookCell.ViewHolder, Book>() {
        @Override
        public void onCellClicked(BookCell bookCell, BookCell.ViewHolder viewHolder, Book item) {
          ToastUtils.show(BasicUsageActivity.this, "click: " + item);
        }
      });
      cell.setOnCellLongClickListener2(new SimpleCell.OnCellLongClickListener2<BookCell, BookCell.ViewHolder, Book>() {
        @Override
        public void onCellLongClicked(BookCell bookCell, BookCell.ViewHolder viewHolder, Book item) {
          ToastUtils.show(BasicUsageActivity.this, "long click: " + item);
        }
      });
      cells.add(cell);
    }

    simpleRecyclerView.addCells(cells);
  }

  private void bindNumbers(GmRecyclerView simpleRecyclerView) {
    List<NumberCell> cells = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      NumberCell cell = new NumberCell(i);
      cells.add(cell);
    }

    simpleRecyclerView.addCells(cells);
  }

}
