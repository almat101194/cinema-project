package com.vedom.cinema.utils

import com.bumptech.glide.request.RequestOptions
import com.vedom.cinema.R

internal val imageOption = RequestOptions()
    .placeholder(R.drawable.ic_baseline_person_24)
    .fallback(R.drawable.ic_baseline_person_24)