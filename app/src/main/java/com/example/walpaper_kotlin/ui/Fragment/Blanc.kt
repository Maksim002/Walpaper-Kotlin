package com.example.walpaper_kotlin.ui.Fragment

import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.adapters.pesonal.PersonalAdapterPayments
import com.example.walpaper_kotlin.Pagination
import com.example.walpaper_kotlin.R
import com.example.walpaper_kotlin.service.WalpaperManager
import com.example.walpaper_kotlin.service.model.WalModel
import com.example.walpaper_kotlin.service.models.Example
import com.example.walpaper_kotlin.ui.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Blanc : Fragment() {
    private lateinit var viewModel: BlanceViewModel
    private var BASE_URL: String = "https://api.unsplash.com/"
    private var host: String = "gW9sjuasObbkxughhFTiEftk-SjD7OuVSvI5aP7bRG4"
    private var key: String = "oqZ_uR-OVEfpANEHtz-u-DXjdR5lyGzMkBl-yKf-4dY"

    private var page: Int = 1

    private lateinit var searchView: SearchView

    val model: WalModel? = null

    private var isLoading = false
    private var isLoadPage = false
    private var CURENT_PAGE = page

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

        setHasOptionsMenu(true)

        val mLayoutManager = LinearLayoutManager(context)

        recyclerView.setLayoutManager(mLayoutManager);

        val service = WalpaperManager.setupRetrofit(BASE_URL)
        service.getMealPlanse(host, key, page)
            .enqueue(object : Callback<List<WalModel>> {
                override fun onResponse(call: Call<List<WalModel>>, response: Response<List<WalModel>>) {


                    var list = ArrayList<String>()
                    for (i in response.body()!!){
                        list.add(i.urls?.thumb!!)
                    }

                    adapters.listUpdate(list)

                    if (CURENT_PAGE <= page){
                    }else{
                        isLoadPage = true
                    }

                }

                override fun onFailure(call: Call<List<WalModel>>, t: Throwable) {
                    t.printStackTrace()
                }
            })

        recyclerView.addOnScrollListener(object : Pagination(mLayoutManager) {
            override fun logMoreItem() {
                isLoading = true
                page++

                Handler().postDelayed({
                    loadNextPage("")
                }, 1000)
            }
            override fun getTiralPegers(): Int {
                return page
            }

            override fun isLoginpage(): Boolean {
                return isLoadPage
            }

            override fun isLoginding(): Boolean {
                return isLoading
            }
        })
    }
    private fun  loadNextPage(tm: String){
        val service = WalpaperManager.setupRetrofit(BASE_URL)
        service.getMealPlanse(host, key, page)
            .enqueue(object : Callback<List<WalModel>> {
                override fun onResponse(call: Call<List<WalModel>>, response: Response<List<WalModel>>) {

                    isLoading = false

                    var list = ArrayList<String>()
                    for (i in response.body()!!){
                        list.add(i.urls?.thumb!!)
                    }

                    adapters.addItem(list)

                    if (CURENT_PAGE <= page){

                    }else{
                        isLoadPage = true
                    }
                }

                override fun onFailure(call: Call<List<WalModel>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    private fun loadNextTap(qvery: String){
        val service = WalpaperManager.setupRetrofit(BASE_URL)
        service.serch(host, key, 1, qvery)
            .enqueue(object : Callback<Example> {
                override fun onResponse(call: Call<Example>, response: Response<Example>) {
                    isLoading = false

                    var map = ArrayList<String>()
                    for (i in arrayListOf(response.body())){
                        for (j in i!!.results){
                            map.add(j.urls.regular)
                        }
                    }
                    adapters.addItem(map)


                    if (CURENT_PAGE <= page){
                    }else{
                        isLoadPage = true
                    }


                }

                override fun onFailure(call: Call<Example>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    private fun search(qvery: String){
        val service = WalpaperManager.setupRetrofit(BASE_URL)
        service.serch(host, key, 1, qvery)
            .enqueue(object : Callback<Example> {
                override fun onResponse(call: Call<Example>, response: Response<Example>) {
                    isLoading = false

                    var map = ArrayList<String>()
                    for (i in arrayListOf(response.body())){
                        for (j in i!!.results){
                            map.add(j.urls.full)
                        }
                    }
                        adapters.listUpdate(map)


                    if (CURENT_PAGE <= page){
                    }else{
                        isLoadPage = true
                    }


                }

                override fun onFailure(call: Call<Example>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)

        searchView = SearchView((context as MainActivity).supportActionBar?.themedContext ?: context)
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
