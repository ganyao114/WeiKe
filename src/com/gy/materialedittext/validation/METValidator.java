package com.gy.materialedittext.validation;

import android.support.annotation.NonNull;


public abstract class METValidator {


  protected String errorMessage;

  public METValidator(@NonNull String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public void setErrorMessage(@NonNull String errorMessage) {
    this.errorMessage = errorMessage;
  }

  @NonNull
  public String getErrorMessage() {
    return this.errorMessage;
  }


  public abstract boolean isValid(@NonNull CharSequence text, boolean isEmpty);

}
