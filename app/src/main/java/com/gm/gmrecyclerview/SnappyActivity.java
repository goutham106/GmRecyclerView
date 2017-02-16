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

import com.gm.gmrecyclerview.cell.NumberCell;
import com.gm.grecyclerview.GmRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SnappyActivity extends BaseActivity {

  @BindView(R.id.centerRecyclerView)
  GmRecyclerView centerRecyclerView;
  @BindView(R.id.startRecyclerView)
  GmRecyclerView startRecyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_snappy);
    ButterKnife.bind(this);
    bindNumber(centerRecyclerView);
    bindNumber(startRecyclerView);
  }

  @OnClick(R.id.centerRadioButton)
  void showCenterRecyclerView() {
    centerRecyclerView.setVisibility(View.VISIBLE);
    startRecyclerView.setVisibility(View.GONE);
  }

  @OnClick(R.id.startRadioButton)
  void showStartRecyclerView() {
    centerRecyclerView.setVisibility(View.GONE);
    startRecyclerView.setVisibility(View.VISIBLE);
  }

  private void bindNumber(GmRecyclerView simpleRecyclerView) {
    List<NumberCell> cells = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      NumberCell cell = new NumberCell(i);
      cells.add(cell);
    }

    simpleRecyclerView.addCells(cells);
  }

}
