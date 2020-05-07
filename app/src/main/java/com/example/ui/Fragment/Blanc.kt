package com.example.ui.Fragment


import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.SearchView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adapter.InvoiceListener
import com.example.tsj.adapters.pesonal.PersonalAdapterPayments
import com.example.utils.Pagination
import com.example.walpaper_kotlin.R
import com.example.utils.DownloadImageTask
import com.example.service.WalpaperManager
import com.example.service.model.Urls
import com.example.service.model.WalModel
import com.example.service.model.Example
import com.example.ui.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Blanc : Fragment(), InvoiceListener {
    private lateinit var viewModel: BlanceViewModel
    private var BASE_URL: String = "https://api.unsplash.com/"
    private var host: String = "gW9sjuasObbkxughhFTiEftk-SjD7OuVSvI5aP7bRG4"
    private var key: String = "oqZ_uR-OVEfpANEHtz-u-DXjdR5lyGzMkBl-yKf-4dY"

    private lateinit var mLayoutManager: GridLayoutManager
    private var qvery = ""

    private lateinit var down_load_image: DownloadImageTask

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

        recyclerView = view.findViewById(R.id.wal_recycler_view)

        adapters = PersonalAdapterPayments(this)
        recyclerView.apply {
            adapter = adapters
        }

        setHasOptionsMenu(true)

        mLayoutManager = GridLayoutManager(context, 2)

        recyclerView.setLayoutManager(mLayoutManager);

        loadNextPage()

        recyclerView.addOnScrollListener(object : Pagination(mLayoutManager) {
            override fun logMoreItem() {
                MainActivity.alert.show()
                isLoading = true
                page++

                Handler().postDelayed({
                    loadNextPage()
                    MainActivity.alert.hide()
                }, 700)
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

    private fun loadNextPage(update: Boolean = false) {

        if (qvery.isNotEmpty()) {

            val service = WalpaperManager.setupRetrofit(BASE_URL)
            service.serch(host, key, page, qvery)
                .enqueue(object : Callback<Example> {
                    override fun onResponse(call: Call<Example>, response: Response<Example>) {
                        isLoading = false

                        val map = ArrayList<Urls>()
                        for (i in arrayListOf(response.body())) {
                            for (j in i!!.results!!) {
                                map.add(j.urlsi!!)
                            }
                        }
                        if (!update) {
                            adapters.addItem(map)
                        } else {
                            adapters.listUpdate(map)
                        }


                        if (CURENT_PAGE <= page) {
                        } else {
                            isLoadPage = true
                        }
                    }

                    override fun onFailure(call: Call<Example>, t: Throwable) {
                        t.printStackTrace()
                    }
                })
        } else {
            val service = WalpaperManager.setupRetrofit(BASE_URL)
            service.getMealPlanse(host, key, page)
                .enqueue(object : Callback<List<WalModel>> {
                    @RequiresApi(Build.VERSION_CODES.M)
                    override fun onResponse(
                        call: Call<List<WalModel>>,
                        response: Response<List<WalModel>>
                    ) {

                        isLoading = false
                        val list = ArrayList<Urls>()

                        for (i in response.body()!!) {
                            i.urls?.let { list.add(it) }
                        }

                        if (!update) {
                            adapters.addItem(list)
                        } else {
                            adapters.listUpdate(list)
                        }

                        if (CURENT_PAGE <= page) {

                        } else {
                            isLoadPage = true
                        }
                    }

                    override fun onFailure(call: Call<List<WalModel>>, t: Throwable) {
                        t.printStackTrace()
                    }
                })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)

        searchView =
            SearchView((context as MainActivity).supportActionBar?.themedContext ?: context)
        menu.findItem(R.id.menu_i).apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
            actionView = searchView
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String): Boolean {
                if (text.equals(qvery)) {
                    loadNextPage()
                } else {
                    page = 1
                    qvery = text
                    loadNextPage(true)
                }
                return false
            }

            override fun onQueryTextChange(text: String): Boolean {
                if (text.isEmpty() && qvery.isNotEmpty()) {
                    page = 1
                    qvery = ""
                    loadNextPage(true)
                }
                return false
            }
        })
        return
    }

    override fun onClickDelete(image: Urls) {
        MainActivity.alert_ins.show()

        down_load_image = DownloadImageTask(requireContext()).execute(image.regular) as DownloadImageTask
    }
}
