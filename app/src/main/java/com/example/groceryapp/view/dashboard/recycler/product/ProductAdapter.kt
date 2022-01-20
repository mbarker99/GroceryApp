package com.example.groceryapp.view.dashboard.recycler.product

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.data.model.response.Product
import com.example.groceryapp.databinding.ItemViewProductBinding
import com.example.groceryapp.view.dashboard.OnItemClickListener

class ProductAdapter(var products: List<Product>, val listener: OnItemClickListener) :
    RecyclerView.Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemViewProductBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)

        holder.itemView.setOnClickListener {
            listener.onProductClicked(product.product_id)
        }

        holder.binding.btnAddToCart.setOnClickListener {
            listener.onAddToCartClicked(position, product)
        }
    }

    override fun getItemCount(): Int = products.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }
}