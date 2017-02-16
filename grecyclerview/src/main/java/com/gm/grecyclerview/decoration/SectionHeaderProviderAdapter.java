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

package com.gm.grecyclerview.decoration;

import android.support.annotation.NonNull;
import android.view.View;

public abstract class SectionHeaderProviderAdapter<T> implements SectionHeaderProvider<T> {

  @NonNull
  public abstract View getSectionHeaderView(T item, int position);

  public abstract boolean isSameSection(T item, T nextItem);

  @Override
  public boolean isSticky() {
    return false;
  }

  @Override
  public int getSectionHeaderMarginTop(T item, int position) {
    return 0;
  }

}
