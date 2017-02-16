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

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gm.gmrecyclerview.cell.BookCell;
import com.gm.gmrecyclerview.cell.NumberCell;
import com.gm.gmrecyclerview.model.Book;
import com.gm.gmrecyclerview.util.DataUtils;
import com.gm.grecyclerview.GmRecyclerView;
import com.gm.grecyclerview.SimpleCell;
import com.gm.grecyclerview.behavior.SwipeToDismissCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.gm.grecyclerview.behavior.SwipeDirection.DOWN;
import static com.gm.grecyclerview.behavior.SwipeDirection.LEFT;
import static com.gm.grecyclerview.behavior.SwipeDirection.RIGHT;
import static com.gm.grecyclerview.behavior.SwipeDirection.UP;

public class SwipeToDismissActivity extends BaseActivity {

  @BindView(R.id.linearVerRecyclerView)
  GmRecyclerView linearVerRecyclerView;
  @BindView(R.id.linearHorRecyclerView)
  GmRecyclerView linearHorRecyclerView;
  @BindView(R.id.resultView)
  TextView resultView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_swipe_to_dismiss);
    ButterKnife.bind(this);
    init();
    bindBooks(linearVerRecyclerView);
    bindNumbers(linearHorRecyclerView);
  }

  private void init() {
    SwipeToDismissCallback<Book> bookCallback = new SwipeToDismissCallback<Book>() {
      // Optional
      @Override
      public boolean enableDefaultFadeOutEffect() {
        // return false if you manipulate custom swipe effect in other 3 callbacks.
        return super.enableDefaultFadeOutEffect();
      }

      // Optional
      @Override
      public void onCellSwiping(GmRecyclerView simpleRecyclerView, View itemView, Book item, int position, Canvas canvas, float dX, float dY, boolean isControlledByUser) {
        resultView.setText("Item " + item + " at position " + position + " is swiping.");
      }

      // Optional
      @Override
      public void onCellSettled(GmRecyclerView simpleRecyclerView, View itemView, Book item, int position) {
        resultView.setText("Item " + item  + " at position " + position + " is settled.");
      }

      @Override
      public void onCellDismissed(GmRecyclerView simpleRecyclerView, Book item, int position) {
        resultView.setText("Item: " + item  + " at position " + position + " is dismissed.");
      }
    };

    SwipeToDismissCallback<Integer> numberCallback = new SwipeToDismissCallback<Integer>() {
      // Optional
      @Override
      public boolean enableDefaultFadeOutEffect() {
        // return false if you manipulate custom swipe effect in other 3 callbacks.
        return super.enableDefaultFadeOutEffect();
      }

      // Optional
      @Override
      public void onCellSwiping(GmRecyclerView simpleRecyclerView, View itemView, Integer item, int position, Canvas canvas, float dX, float dY, boolean isControlledByUser) {
        resultView.setText("Item " + item + " at position " + position + " is swiping.");
      }

      // Optional
      @Override
      public void onCellSettled(GmRecyclerView simpleRecyclerView, View itemView, Integer item, int position) {
        resultView.setText("Item " + item  + " at position " + position + " is settled.");
      }

      @Override
      public void onCellDismissed(GmRecyclerView simpleRecyclerView, Integer item, int position) {
        resultView.setText("Item: " + item  + " at position " + position + " is dismissed.");
      }
    };

    linearVerRecyclerView.enableSwipeToDismiss(bookCallback, LEFT, RIGHT);
    linearHorRecyclerView.enableSwipeToDismiss(numberCallback, UP, DOWN);
  }

  @OnClick(R.id.linearVerRadioButton)
  void showLinearVerRecyclerView() {
    linearVerRecyclerView.setVisibility(View.VISIBLE);
    linearHorRecyclerView.setVisibility(View.GONE);
    resultView.setText("");
  }

  @OnClick(R.id.linearHorRadioButton)
  void showLinearHorRecyclerView() {
    linearVerRecyclerView.setVisibility(View.GONE);
    linearHorRecyclerView.setVisibility(View.VISIBLE);
    resultView.setText("");
  }

  private void bindBooks(GmRecyclerView simpleRecyclerView) {
    List<Book> books = DataUtils.getBooks();
    List<SimpleCell> cells = new ArrayList<>();

    for (Book book : books) {
      BookCell cell = new BookCell(book);
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
