package com.example.walpaper_kotlin.ui.Fragment

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.adapters.pesonal.PersonalAdapterPayments
import com.example.walpaper_kotlin.R
import com.example.walpaper_kotlin.service.WalpaperManager
import com.example.walpaper_kotlin.service.model.WalModel
import com.example.walpaper_kotlin.ui.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Blanc : Fragment() {
    private lateinit var viewModel: BlanceViewModel
    private var BASE_URL: String = "https://api.unsplash.com/"
    private var host: String = "gW9sjuasObbkxughhFTiEftk-SjD7OuVSvI5aP7bRG4"
    private var key: String = "oqZ_uR-OVEfpANEHtz-u-DXjdR5lyGzMkBl-yKf-4dY"

    private var page: Int = 0
    private var tm: String? = null

    private var isLoading = true
    private var dwdwd: Int = 0
    private var pastVisibleItem: Int = 0;
    private var visibleItemCount: Int = 0;
    private var totalItemCount: Int = 0;
    private var view_threshold: Int = 30;

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapters: PersonalAdapterPayments

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(BlanceViewModel::class.java)
        return inflater.inflate(R.layout.fragment_blanc, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.wal_recycler_view);
        adapters = PersonalAdapterPayments()
        recyclerView.apply {
            adapter = adapters
        }
        loadNextPage("")

        setHasOptionsMenu(true)

        val mLayoutManager = LinearLayoutManager(context)

        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                visibleItemCount = mLayoutManager.childCount
                totalItemCount = mLayoutManager.itemCount
                pastVisibleItem = mLayoutManager.findFirstCompletelyVisibleItemPosition()

                if (isLoading) {
                    if (totalItemCount > view_threshold) {
                        view_threshold = totalItemCount
                        page++
                        isLoading = false
                    }
                }
                if (!isLoading && (pastVisibleItem + visibleItemCount) >= totalItemCount) {
                    page++
                    loadNextPage("")
                    isLoading = true
                }

            }
        })
    }
    private fun  loadNextPage(tm: String){
        val service = WalpaperManager.setupRetrofit(BASE_URL)
        service.getMealPlanse(host, key, page)
            .enqueue(object : Callback<List<WalModel>> {
                override fun onResponse(call: Call<List<WalModel>>, response: Response<List<WalModel>>) {

                    adapters.listUpdate(response.body()!!)
                    adapters.addBittom()
                }

                override fun onFailure(call: Call<List<WalModel>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    private fun search(qvery: String){
        val service = WalpaperManager.setupRetrofit(BASE_URL)
        service.serch(host, key, page, qvery)
            .enqueue(object : Callback<WalModel> {
                override fun onResponse(call: Call<WalModel>, response: Response<WalModel>) {
                    response.body()
                }

                override fun onFailure(call: Call<WalModel>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)

        val searchView = SearchView((context as MainActivity).supportActionBar?.themedContext ?: context)
        menu.findItem(R.id.menu_i).apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
            actionView = searchView
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                search(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                search(newText)
                return false
            }
        })
        return
    }
}
