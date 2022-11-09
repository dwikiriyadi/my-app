package io.github.dwikiriyadi.myapp.features.note.write

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.dwikiriyadi.myapp.core.objects.entity.Page
import io.github.dwikiriyadi.myapp.core.viewmodels.write.WriteViewModel
import io.github.dwikiriyadi.myapp.R
import io.github.dwikiriyadi.myapp.databinding.FragmentWriteBinding

@AndroidEntryPoint
class WriteFragment : androidx.fragment.app.Fragment() {

    private lateinit var binding: FragmentWriteBinding
    private val viewModel by viewModels<WriteViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as AppCompatActivity).apply {
            title = ""
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentWriteBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.get(requireArguments().getLong("io"))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.write_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(requireActivity(), R.id.container)

        when (item.itemId) {
            android.R.id.home -> {
                if (requireArguments().getString("event") == "add") {
                    val page = viewModel.page.value
                    viewModel.insert(
                        Page(
                            section_id = requireArguments().getLong("section_id"),
                            title = page?.title,
                            content = page?.content
                        )
                    )
                } else {
                    viewModel.update(viewModel.page.value!!)
                }

                return true
            }
            R.id.delete -> {
                viewModel.delete(viewModel.page.value!!)
                navController.popBackStack()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
