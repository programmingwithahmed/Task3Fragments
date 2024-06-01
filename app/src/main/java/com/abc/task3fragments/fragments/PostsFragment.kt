package com.abc.task3fragments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.abc.task3fragments.R
import com.abc.task3fragments.adapters.PostsAdapter
import com.abc.task3fragments.databinding.FragmentPostsBinding
import com.abc.task3fragments.models.Post
import com.abc.task3fragments.utils.GeneralUtility
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * A simple [Fragment] subclass.
 * Use the [PostsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostsFragment : Fragment() {

    private lateinit var binding: FragmentPostsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostsBinding.inflate(inflater)
        setUp()
        return binding.root
    }

    private fun setUp() {
        val jsonString = GeneralUtility.readTextFileFromAssets(requireContext(), "task.txt")

        val type = object : TypeToken<List<Post>>() {}.type
        val posts: List<Post> = Gson().fromJson(jsonString, type)


        val postsAdapter = PostsAdapter(posts)
        postsAdapter.onClickItem = { position ->
            parentFragmentManager.beginTransaction().apply {
                add(R.id.fragment_container, PostDetailsFragment.newInstance(posts[position]))
                addToBackStack("PostDetailsFragment")
                commit()
            }
        }

        binding.rvPosts.adapter = postsAdapter
    }

    companion object {
        fun newInstance() = PostsFragment()
    }
}