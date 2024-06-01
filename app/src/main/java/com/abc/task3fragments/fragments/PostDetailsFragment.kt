package com.abc.task3fragments.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.BundleCompat
import com.abc.task3fragments.R
import com.abc.task3fragments.databinding.FragmentPostDetailsBinding
import com.abc.task3fragments.models.Post
import com.abc.task3fragments.utils.Constants
import com.bumptech.glide.Glide

class PostDetailsFragment : Fragment() {
 private lateinit var binding: FragmentPostDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostDetailsBinding.inflate(inflater)
        setUp()
        return binding.root
    }

    private fun setUp(){
        arguments?.let {bundle ->

            val post = BundleCompat.getParcelable(bundle, Constants.POST, Post::class.java)

            post?.let {

                binding.apply {

                    Glide.with(requireContext()).load(post.url).into(ivPost)

                    tvTitle.text = post.title
                    tvBody.text = post.body
                }

            }

        }
    }

    companion object {

        fun newInstance(post: Post) =
            PostDetailsFragment().apply {

                val bundle = Bundle()
                bundle.putParcelable(Constants.POST, post)
                arguments = bundle


//                arguments = Bundle().apply {
//                    putParcelable(Constants.POST, post)
//                }

            }
    }
}