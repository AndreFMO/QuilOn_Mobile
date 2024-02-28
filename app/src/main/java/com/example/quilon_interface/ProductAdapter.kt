package com.example.quilon_interface

import Produto
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayout

class ProductAdapter(
    private val productList: List<Produto>,
    private val imageList: MutableMap<Int, Bitmap>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.idTitulo1)
        val priceTextView: TextView = itemView.findViewById(R.id.idPreco1)
        val productImageView: ImageView = itemView.findViewById(R.id.id_Imagem_do_produto)
        val flexboxLayout: FlexboxLayout = itemView.findViewById(R.id.flexboxLayout)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item_layout, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        // Configurar os dados para exibição
        holder.titleTextView.text = product.title
        holder.priceTextView.text = product.price.toString()

        // Configurar o ID do produto como uma tag para a flexBox
        holder.flexboxLayout.tag = product.id

        // Configurar o ouvinte de clique para a FlexboxLayout
        holder.flexboxLayout.setOnClickListener {
            val clickedProductId = it.tag as? Int
            clickedProductId?.let { productId ->
                onItemClick(productId)
            }
        }

        // Configurar a imagem
        val productImage = imageList[product.id]
        if (productImage != null) {
            holder.productImageView.setImageBitmap(productImage)
        } else {
            // Se a imagem não estiver disponível, você pode definir uma imagem padrão ou lidar de outra forma
            holder.productImageView.setImageResource(R.drawable.imagem_do_produto)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun updateImageList(newImageList: Map<Int, Bitmap>) {
        imageList.clear()
        imageList.putAll(newImageList)
        notifyDataSetChanged()
    }
}
