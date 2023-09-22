package com.bignerdranch.android.geoquiz

import androidx.annotation.StringRes

data class Question(@StringRes val testResId: Int, val answer: Boolean)