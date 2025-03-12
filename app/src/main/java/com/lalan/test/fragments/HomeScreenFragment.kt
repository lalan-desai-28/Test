package com.lalan.test.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lalan.test.MyApplication
import com.lalan.test.R
import com.lalan.test.adapter.DashboardPostAdapter
import com.lalan.test.viewmodel.DashboardDataViewModel


class HomeScreenFragment(private val dashboardDataViewModel: DashboardDataViewModel) : Fragment() {

    private lateinit var mainToolBar: Toolbar
    private lateinit var mainRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home_screen, container, false)

        mainToolBar = view.findViewById(R.id.mainToolBar)
        mainRecyclerView = view.findViewById(R.id.mainRecyclerView)
        progressBar = view.findViewById(R.id.progressBar)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userData = MyApplication.userProfile

        mainToolBar.setTitle("Hello, ${userData?.data?.name}")

        dashboardDataViewModel.getDashboardData(MyApplication.sessionToken)

        dashboardDataViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }


        dashboardDataViewModel.dashboardDataResult.observe(viewLifecycleOwner) { result ->
            if (result?.code() == 200) {
                mainRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                mainRecyclerView.adapter =
                    DashboardPostAdapter(
                        result.body()?.data?.toList()?.sortedByDescending { it.post?.media?.size }
                            ?: emptyList(), dashboardDataViewModel)
            }
        }
    }
}