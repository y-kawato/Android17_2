package jp.co.stv_tech.android_17_2

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setLogo(R.mipmap.ic_launcher)
        setSupportActionBar(toolbar)
        val toolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.toolbarLayout)
        toolbarLayout.title = getString(R.string.toolbar_title)
        toolbarLayout.setExpandedTitleColor(Color.WHITE)
        toolbarLayout.setCollapsedTitleTextColor(Color.LTGRAY)

        val lvMenu = findViewById<RecyclerView>(R.id.lvMenu)
        val layout = LinearLayoutManager(applicationContext)
        lvMenu.layoutManager = layout
        val menuList = createTeishokuList()
        val adapter = RecyclerListAdapter(menuList)
        lvMenu.adapter = adapter
    }
    private fun createTeishokuList():MutableList<MutableMap<String, Any>> {

        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()

        var menu = mutableMapOf<String, Any>("name" to "唐揚げ定食", "price" to 800,"desc" to "若鳥の唐揚げにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)

        menu = mutableMapOf<String, Any>("name" to "ハンバーグ定食", "price" to 800,"desc" to "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)

        return menuList

    }
    private inner class RecyclerListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvMenuName: TextView
        var tvMenuPrice: TextView

        init {
            tvMenuName = itemView.findViewById(R.id.tvMenuName)
            tvMenuPrice = itemView.findViewById(R.id.tvMenuPrice)
        }
    }
    private inner class RecyclerListAdapter(private val _listData: MutableList<MutableMap<String, Any>>):
        RecyclerView.Adapter<RecyclerListViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerListViewHolder {
            val inflater = LayoutInflater.from(applicationContext)

            val view = inflater.inflate(R.layout.row, parent, false)
            val holder = RecyclerListViewHolder(view)
            return holder
        }
        override fun onBindViewHolder(holder: RecyclerListViewHolder, position: Int) {
            val item = _listData[position]
            val menuName = item["name"] as String
            val menuPrice = item["price"] as Int
            val menuPriceStr = menuPrice.toString()

            holder.tvMenuName.text = menuName
            holder.tvMenuPrice.text = menuPriceStr
        }
        override fun getItemCount(): Int {
            return _listData.size
        }
    }

}
