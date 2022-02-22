package com.bignerdranch.android.criminalintent

import androidx.lifecycle.ViewModel

class CrimeListViewModel : ViewModel() {
    //Crime 객체를 저장한 리스트
    val crimes = mutableListOf<Crime>()

    init{
        for(i in 0 until 100){
            val crime = Crime()
            crime.title = "Crime #$i"
            crime.isSolved = i%2 == 0
            crimes += crime
        }
    }
}