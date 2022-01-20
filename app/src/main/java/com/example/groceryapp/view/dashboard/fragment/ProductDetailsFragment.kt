package com.example.groceryapp.view.dashboard.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.R
import com.example.groceryapp.data.model.response.Product
import com.example.groceryapp.data.model.response.Review
import com.example.groceryapp.data.remote.APIConstants
import com.example.groceryapp.data.remote.VolleyRequestHandler
import com.example.groceryapp.databinding.FragmentProductDetailsBinding
import com.example.groceryapp.presenter.dashboard.product_details_fragment.ProductDetailsContract
import com.example.groceryapp.presenter.dashboard.product_details_fragment.ProductDetailsPresenter
import com.example.groceryapp.presenter.dashboard.subcategories_fragment.SubcategoriesPresenter
import com.example.groceryapp.view.dashboard.recycler.product_images.ImageAdapter
import com.example.groceryapp.view.dashboard.recycler.review.ReviewAdapter
import com.example.groceryapp.view.dashboard.recycler.specifications.SpecificationAdapter
import com.squareup.picasso.Picasso

class ProductDetailsFragment : Fragment(), ProductDetailsContract.View {
    lateinit var binding: FragmentProductDetailsBinding
    lateinit var presenter: ProductDetailsPresenter
    lateinit var reviewAdapter: ReviewAdapter
    lateinit var specsAdapter: SpecificationAdapter
    lateinit var imageAdapter: ImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)

        val volleyRequestHandler = VolleyRequestHandler(requireActivity().applicationContext)
        presenter = ProductDetailsPresenter(volleyRequestHandler, this)
        reviewAdapter = ReviewAdapter(emptyList())
        specsAdapter = SpecificationAdapter(emptyList())
        imageAdapter = ImageAdapter(emptyList())

        binding.apply {
            rvSpecifications.layoutManager = LinearLayoutManager(requireContext())
            rvReviews.layoutManager = LinearLayoutManager(requireContext())
            rvProductImages.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

            rvSpecifications.adapter = specsAdapter
            rvReviews.adapter = reviewAdapter
            rvProductImages.adapter = imageAdapter
        }

        val productId = arguments?.getString("id")

        presenter.getProductDetails(productId as String)



        return binding.root
    }

    override fun onLoad(isLoading: Boolean) {
        if (isLoading)
            binding.progressHorizontal.visibility = View.VISIBLE
        else
            binding.progressHorizontal.visibility = View.GONE
    }

    override fun setResult(product: Product?) {
        binding.apply {
            if (product != null) {
                tvProductName.text = product.product_name
                tvPrice.text = "\$${product.price}"
                tvProductDescription.text = product.description
                rbProductRating.rating = product.average_rating.toFloat()
                val imageUrl = "${APIConstants.BASE_IMAGE_URL}${product.product_image_url}"
                Picasso.get().load(imageUrl).placeholder(R.drawable.ic_image_placeholder)
                    .into(ivProductImage)
                reviewAdapter.update(product.reviews)
                specsAdapter.update(product.specifications)
                imageAdapter.update(product.images)

            }
        }


    }


}