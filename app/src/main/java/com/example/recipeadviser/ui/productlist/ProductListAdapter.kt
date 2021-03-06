package com.example.recipeadviser.ui.productlist

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.CheckBox
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.example.recipeadviser.R
import com.example.recipeadviser.SerializableIngredients
import com.example.recipeadviser.convertToSerializableIngredients
import com.example.recipeadviser.localdatabase.ingredients.IngredientData
import com.example.recipeadviser.localdatabase.ingredients.UserIngredient
import kotlinx.coroutines.runBlocking

class ProductListAdapter(i_context: Context, i_ingredients: LiveData<List<IngredientData>>, i_userIngredients: LiveData<List<UserIngredient>>,
                         viewLifecycleOwner: LifecycleOwner, i_productsViewModel: ProductListViewModel) : BaseExpandableListAdapter() {
    val context: Context = i_context
    val productsViewModel: ProductListViewModel = i_productsViewModel

    var ingredients: LiveData<List<IngredientData>> = i_ingredients
    var userIngredients: LiveData<List<UserIngredient>> = i_userIngredients

    var expandableListTitle = mutableListOf<String>()
    var expandableListDetail = hashMapOf<String, MutableList<SerializableIngredients>>()

    var list = mutableListOf<ProductListIngredientInfo>()
    init{
        ingredients.observe(viewLifecycleOwner, { list ->
            updateData()
        })

        userIngredients.observe(viewLifecycleOwner, { list ->
            updateData()
        })
    }

    private fun updateData()
    {
        expandableListDetail.clear()
        expandableListTitle.clear()

        if (ingredients.value != null) {
            for (ingr in ingredients.value!!) {
                addIngredient(convertToSerializableIngredients(ingr))
            }
        }

        if (userIngredients.value != null) {
            for (ingr in userIngredients.value!!) {
                addIngredient(convertToSerializableIngredients(ingr))
            }
        }

        expandableListTitle = ArrayList(expandableListDetail.keys)
        notifyDataSetChanged()
    }

    private fun addIngredient(serIngr: SerializableIngredients)
    {
        if (!expandableListDetail.containsKey(serIngr.type)) {
            expandableListDetail.put(serIngr.type!!, mutableListOf())
        }
        val current = expandableListDetail.get(serIngr.type)!!.find { it.name == serIngr.name }
        if (current != null) {
            current.addAmount(serIngr.amount!!)
        } else {
            expandableListDetail.get(serIngr.type)!!.add(serIngr)
        }
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return this.expandableListDetail.get(this.expandableListTitle.get(groupPosition))?.get(childPosition).toString()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        val obj =expandableListDetail.get(this.expandableListTitle.get(groupPosition))?.get(childPosition)

        val expandedListText = obj.toString()
        if (convertView == null) {
            val layoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.product_list_item, null)
        }
        val expandedCheckBox = convertView?.findViewById(R.id.expanded_list_item) as CheckBox
        val textView = convertView.findViewById(R.id.text_expanded_list_item) as TextView
        textView.text = expandedListText

        obj!!.ingredientId?.let {
            val _list = productsViewModel.getAll()
            if (list != _list) {
                    var name = ""
                    var n = ""
                for (l in _list) {
                    if (l.state)
                        name += l.name
                    else
                        n += l.name
                }
                println("let:" + name + " | " + n)
                list = _list
            }
            val list = productsViewModel.find(it, obj.name!!)
            var checked = false
            if (!list.isEmpty())
            {
                checked = list[0].state
            }
            expandedCheckBox.setChecked(checked)
            if (checked)
                textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            else
                textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        expandedCheckBox.setOnCheckedChangeListener { compoundButton, isChecked ->

            //if(compoundButton.isPressed()) {

                textView.paintFlags = textView.paintFlags xor Paint.STRIKE_THRU_TEXT_FLAG

                runBlocking {
                    productsViewModel.addCheckedIngredient(
                        ProductListIngredientInfo(
                            obj.ingredientId!!,
                            obj.name!!,
                            isChecked
                        )
                    )
                    val _list = productsViewModel.getAll()
                    if (list != _list) {
                        var name = ""

                        for (l in _list) {
                            if (l.state)
                                name += l.name
                        }
                        println("listener:" + name)
                        list = _list
                    }
                }
           //}
            //else
            //if(!compoundButton.isPressed())
               println(" butt.id -" + compoundButton.id +
                       " isPre " + compoundButton.isPressed())
        }

        return convertView
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        val listTitle = getGroup(groupPosition) as String
        if (convertView == null) {
            val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.product_list_group, null)
        }
        val listTitleTextView = convertView!!.findViewById(R.id.list_title) as TextView
        listTitleTextView.setTypeface(null, Typeface.BOLD)
        listTitleTextView.text = listTitle
        return convertView
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return this.expandableListDetail.get(this.expandableListTitle.get(groupPosition))?.size!!
    }

    override fun getGroup(groupPosition: Int): Any {
        return this.expandableListTitle.get(groupPosition)
    }

    override fun getGroupCount(): Int {
        return this.expandableListTitle.size
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

}
