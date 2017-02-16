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

import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.basicUsageButton)
  void goToLayoutModePage() {
    startActivity(new Intent(this, BasicUsageActivity.class));
  }

  @OnClick(R.id.multiTypesButton)
  void goToMultiTypesPage() {
    startActivity(new Intent(this, MultiTypesActivity.class));
  }

  @OnClick(R.id.cellOperationsButton)
  void goToCellOperationsPage() {
    startActivity(new Intent(this, CellOperationsActivity.class));
  }

  @OnClick(R.id.dividerButton)
  void goToDividerPage() {
    startActivity(new Intent(this, DividerActivity.class));
  }

  @OnClick(R.id.spacingButton)
  void goToSpacingPage() {
    startActivity(new Intent(this, SpacingActivity.class));
  }

  @OnClick(R.id.emptyViewButton)
  void goToEmptyViewPage() {
    startActivity(new Intent(this, EmptyStateViewActivity.class));
  }

  @OnClick(R.id.sectionHeaderButton)
  void goToSectionHeaderPage() {
    startActivity(new Intent(this, SectionHeaderActivity.class));
  }

  @OnClick(R.id.loadMoreViewButton)
  void goToLoadMoreViewPage() {
    startActivity(new Intent(this, AutoLoadMoreActivity.class));
  }

  @OnClick(R.id.dragAndDropButton)
  void goToDragAndDropPage() {
    startActivity(new Intent(this, DragAndDropActivity.class));
  }

  @OnClick(R.id.swipeToDismissButton)
  void goToSwipeToDismissPage() {
    startActivity(new Intent(this, SwipeToDismissActivity.class));
  }

  @OnClick(R.id.snappyButton)
  void goToSnappyPage() {
    startActivity(new Intent(this, SnappyActivity.class));
  }

}
