package com.bignerdranch.android.criminalintent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "CrimeListFragment"
class CrimeListFragment : Fragment() {

    private lateinit var crimeRecyclerView: RecyclerView
    private var adapter: CrimeAdapter? = null

    // provider를 호출하면 crimelistfragment와 연관된 viewModelprovider인스턴스를 생성하고 반환.
    //fragment인 crimelistfragment가 뷰모델인 crimelistviewmodel과 연결
    private val crimeListViewModel: CrimeListViewModel by lazy{
        ViewModelProvider(this).get(CrimeListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total crimes : ${crimeListViewModel.crimes.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crime_list, container, false)

        //리사이클러뷰 생성 후에는 반드시 바로 linearlayoutmanager를 설정해주어야한다.
        //설정하지 않으면 동작하지 않음! -> 리사이클러뷰가 항목 위치 시키는 일을 layoutmanager에 위임함.
        crimeRecyclerView = view.findViewById(R.id.crime_recycler_view) as RecyclerView
        crimeRecyclerView.layoutManager = LinearLayoutManager(context)

        // CrimeListFragment의 ui를 설정하는 updateUI함수 구현
        updateUI()

        return view
    }

    private fun updateUI(){
        val crimes = crimeListViewModel.crimes
        adapter = CrimeAdapter(crimes)
        // 코들린에서는 속성의 값을 보존하는 필드를 내부적으로 유지함. 이를 후원필드 (backing field)
        crimeRecyclerView.adapter = adapter

    }

    //recyclerView는 자체적으로 view를 생성하지 않고 항상 항목view를 참조하는 viewHolder를 생성.
    private inner class CrimeHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener{
        private lateinit var crime: Crime

        private val titleTextView: TextView = itemView.findViewById(R.id.crime_title)
        private val dateTextView: TextView = itemView.findViewById(R.id.crime_date)
        private val solvedImageView: ImageView = itemView.findViewById(R.id.crime_solved)

        private val dateFormat = SimpleDateFormat("EEE, MMM dd, yyyy")

        init{
            itemView.setOnClickListener(this)
        }

        // 데이터 바인딩을 수행하는 모든 코드는 crimeholder내부에 두는 것이 조음
        //아래 함수에서 바인딩 되는 crime객체의 참조를 새로 추가하는 속성에 보존하며, 현재 참조되는 crime객체의 속성값으로 각 textview의 text속성값을 설정
        fun bind(crime: Crime){
            this.crime = crime
            titleTextView.text = this.crime.title

            dateTextView.text = dateFormat.format(Calendar.getInstance().time)
            solvedImageView.visibility = if (crime.isSolved){
                View.VISIBLE
            }else{
                View.GONE
            }
        }

        override fun onClick(v: View) {
            Toast.makeText(context,"${crime.title} pressed!", Toast.LENGTH_SHORT).show()
        }
    }

    // 어댑터는 recyclerview의 viewholder를 대신 생성해줌
    private inner class CrimeAdapter(var crimes: List<Crime>): RecyclerView.Adapter<CrimeHolder>(){
        //보여줄 뷰를 인플레이트 하고 이 뷰를 처리하는 인스턴스 생성 후 반환
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
            val view = layoutInflater.inflate(R.layout.list_item_crime, parent, false)
            return CrimeHolder(view)
        }

        // 속성들 지정
        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            val crime = crimes[position]
            //리사이클러가 요청할 때마다 bind함수 호출하도록 변경
            holder.bind(crime)
        }

        override fun getItemCount() = crimes.size
    }

    //crimelistfragment 인스턴스를 생성하고 반환한다.
    companion object{
        // newInstance함수는 자바의 static 과 유사
        //액티비티에서 crimelistfragment 인스턴스를 생성하려면 newInstance함수를 호출하면 된다.
        fun newInstance(): CrimeListFragment{
            return CrimeListFragment()
        }
    }
}