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
import android.widget.TextView;

import com.gm.gmrecyclerview.cell.BookCell;
import com.gm.gmrecyclerview.model.Book;
import com.gm.gmrecyclerview.util.DataUtils;
import com.gm.grecyclerview.GmRecyclerView;
import com.gm.grecyclerview.SimpleCell;
import com.gm.grecyclerview.behavior.DragAndDropCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DragAndDropActivity extends BaseActivity {

  @BindView(R.id.linearRecyclerView)
  GmRecyclerView linearRecyclerView;
  @BindView(R.id.gridRecyclerView)
  GmRecyclerView gridRecyclerView;
  @BindView(R.id.resultView)
  TextView resultView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_drag_and_drop);
    ButterKnife.bind(this);
    init();
    bindBooks(linearRecyclerView, true);
    bindBooks(gridRecyclerView, false);
  }

  private void init() {
    DragAndDropCallback<Book> dragAndDropCallback = new DragAndDropCallback<Book>() {
      // Optional
      @Override
      public boolean enableDefaultRaiseEffect() {
        // return false if you manipulate custom drag effect in other 3 callbacks.
        return super.enableDefaultRaiseEffect();
      }

      // Optional
      @Override
      public void onCellDragStarted(GmRecyclerView simpleRecyclerView, View itemView, Book item, int position) {
        resultView.setText("Started dragging " + item + " at position " + position);
      }

      // Optional
      @Override
      public void onCellMoved(GmRecyclerView simpleRecyclerView, View itemView, Book item, int fromPosition, int toPosition) {
        resultView.setText("Moved " + item + " from position " + fromPosition + " to position " + toPosition);
      }

      @Override
      public void onCellDropped(GmRecyclerView simpleRecyclerView, View itemView, Book item, int fromPosition, int toPosition) {
        resultView.setText("Dragged " + item + " from position " + fromPosition + " to position " + toPosition);
      }

      @Override
      public void onCellDragCancelled(GmRecyclerView simpleRecyclerView, View itemView, Book item, int currentPosition) {
        resultView.setText("Cancelled dragging " + item + " at position " + currentPosition);
      }
    };

    linearRecyclerView.enableDragAndDrop(R.id.dragHandle, dragAndDropCallback);
    gridRecyclerView.enableDragAndDrop(dragAndDropCallback);
  }

  @OnClick(R.id.linearRadioButton)
  void showLinearRecyclerView() {
    linearRecyclerView.setVisibility(View.VISIBLE);
    gridRecyclerView.setVisibility(View.GONE);
    resultView.setText("");
  }

  @OnClick(R.id.gridRadioButton)
  void showGridRecyclerView() {
    linearRecyclerView.setVisibility(View.GONE);
    gridRecyclerView.setVisibility(View.VISIBLE);
    resultView.setText("");
  }

  private void bindBooks(GmRecyclerView simpleRecyclerView, boolean showHandle) {
    List<Book> books = DataUtils.getBooks();
    List<SimpleCell> cells = new ArrayList<>();

    for (Book book : books) {
      BookCell cell = new BookCell(book);
      cell.setShowHandle(showHandle);
      cells.add(cell);
    }

    simpleRecyclerView.addCells(cells);
  }

}
