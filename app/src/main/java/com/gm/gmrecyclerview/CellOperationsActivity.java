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

import com.gm.gmrecyclerview.cell.BookCell;
import com.gm.gmrecyclerview.model.Book;
import com.gm.gmrecyclerview.util.DataUtils;
import com.gm.grecyclerview.GmRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CellOperationsActivity extends BaseActivity {

  @BindView(R.id.recyclerView)
  GmRecyclerView recyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cell_operations);
    ButterKnife.bind(this);
    bindBooks();
  }

  @OnClick(R.id.addButton)
  void add() {
    int itemCount = recyclerView.getItemCount();
    Book book = DataUtils.newBook(itemCount);
    BookCell cell = new BookCell(book);
    recyclerView.addCell(cell);
  }

  @OnClick(R.id.addOrUpdateButton)
  void addOrUpdate() {
    Book book0 = DataUtils.getBook(0);
    book0.setTitle("(Updated) Book 0");
    Book book1 = DataUtils.getBook(1);
    book1.setTitle("(Updated) Book 1");
    Book newBook1 = DataUtils.newBook(recyclerView.getItemCount());
    Book newBook2 = DataUtils.newBook(recyclerView.getItemCount() + 1);
    List<BookCell> cells = new ArrayList<>();
    cells.add(new BookCell(book0));
    cells.add(new BookCell(book1));
    cells.add(new BookCell(newBook1));
    cells.add(new BookCell(newBook2));
    recyclerView.addOrUpdateCells(cells);
  }

  @OnClick(R.id.updateButton)
  void update() {
    if (recyclerView.isEmpty()) {
      return;
    }

    Book book1 = DataUtils.getBook(1);
    book1.setTitle("(Updated) Book 1");

    recyclerView.updateCell(1, book1);
  }

  @OnClick(R.id.removeButton)
  void remove() {
    if (recyclerView.isEmpty()) {
      return;
    }

    recyclerView.removeCell(recyclerView.getItemCount() - 1);
  }

  private void bindBooks() {
    List<Book> books = DataUtils.getBooks(3);
    List<BookCell> cells = new ArrayList<>();

    for (Book book : books) {
      BookCell cell = new BookCell(book);
      cells.add(cell);
    }

    recyclerView.addCells(cells);
  }

}
