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
import android.support.v4.widget.SwipeRefreshLayout;


import com.gm.gmrecyclerview.cell.BookCell;
import com.gm.gmrecyclerview.model.Book;
import com.gm.gmrecyclerview.util.DataUtils;
import com.gm.grecyclerview.GmRecyclerView;
import com.gm.grecyclerview.SimpleCell;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmptyStateViewActivity extends BaseActivity {

  @BindView(R.id.gridRecyclerView)
  GmRecyclerView recyclerView;
  @BindView(R.id.swipeRefreshLayout)
  SwipeRefreshLayout swipeRefreshLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_empty_view);
    ButterKnife.bind(this);

    init();
    loadBooksFromNetwork();
  }

  private void init() {
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        clearBooks();
        loadBooksFromNetwork();
      }
    });
  }

  @OnClick(R.id.addButton)
  void addCell() {
    int itemCount = recyclerView.getItemCount();
    Book book = DataUtils.newBook(itemCount);
    BookCell cell = new BookCell(book);
    recyclerView.addCell(cell);
  }

  @OnClick(R.id.removeButton)
  void removeAllCell() {
    if (recyclerView.isEmpty()) {
      return;
    }
    recyclerView.removeAllCells();
  }

  private void loadBooksFromNetwork() {
    swipeRefreshLayout.setRefreshing(true);
    DataUtils.getBooksAsync(this, new DataUtils.DataCallback() {
      @Override
      public void onSuccess(List<Book> books) {
        bindBooks(books);
        swipeRefreshLayout.setRefreshing(false);
      }
    });
  }

  private void clearBooks() {
    recyclerView.removeAllCells(false);
  }

  private void bindBooks(List<Book> books) {
    List<SimpleCell> cells = new ArrayList<>();

    for (Book book : books) {
      BookCell cell = new BookCell(book);
      cells.add(cell);
    }

    recyclerView.addCells(cells);
  }

}
