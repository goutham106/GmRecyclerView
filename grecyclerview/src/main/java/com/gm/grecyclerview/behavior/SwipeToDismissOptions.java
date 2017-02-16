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

package com.gm.grecyclerview.behavior;

import java.util.HashSet;
import java.util.Set;

public class SwipeToDismissOptions {

  private Set<SwipeDirection> swipeDirections = new HashSet<>();
  private boolean enableDefaultFadeOutEffect = true;
  private SwipeToDismissCallback swipeToDismissCallback;

  public boolean isDefaultFadeOutEffectEnabled() {
    return enableDefaultFadeOutEffect;
  }

  public void setEnableDefaultFadeOutEffect(boolean enableDefaultFadeOutEffect) {
    this.enableDefaultFadeOutEffect = enableDefaultFadeOutEffect;
  }

  public SwipeToDismissCallback getSwipeToDismissCallback() {
    return swipeToDismissCallback;
  }

  public void setSwipeToDismissCallback(SwipeToDismissCallback swipeToDismissCallback) {
    this.swipeToDismissCallback = swipeToDismissCallback;
  }

  public void setSwipeDirections(Set<SwipeDirection> swipeDirections) {
    this.swipeDirections = new HashSet<>(swipeDirections);
  }

  public boolean canSwipeLeft() {
    return swipeDirections.contains(SwipeDirection.LEFT);
  }


  public boolean canSwipeRight() {
    return swipeDirections.contains(SwipeDirection.RIGHT);
  }

  public boolean canSwipeUp() {
    return swipeDirections.contains(SwipeDirection.UP);
  }

  public boolean canSwipeDown() {
    return swipeDirections.contains(SwipeDirection.DOWN);
  }

}
