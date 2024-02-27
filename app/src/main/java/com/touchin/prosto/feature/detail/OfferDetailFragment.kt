package com.touchin.prosto.feature.detail

import android.util.Log
import androidx.navigation.fragment.navArgs
import com.anadolstudio.core.viewbinding.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.touchin.prosto.R
import com.touchin.prosto.base.bottom.BaseContentBottom
import com.touchin.prosto.databinding.FragmentOfferDetailBinding
import com.touchin.prosto.di.viewmodel.assistedViewModel
import com.touchin.prosto.util.GradientDrawable

@Suppress("TooManyFunctions")
class OfferDetailFragment : BaseContentBottom<OfferDetailState, OfferDetailViewModel, OfferDetailController>(
    R.layout.fragment_offer_detail
) {

    private val binding by viewBinding { FragmentOfferDetailBinding.bind(it) }
    protected val args: OfferDetailFragmentArgs by navArgs()

    override fun createViewModelLazy() = assistedViewModel {
        getSharedComponent()
            .offerDetailViewModelFactory()
            .create(args.offer)
    }

    override fun render(state: OfferDetailState, controller: OfferDetailController) {
        val offerItem = state.offer
        with(binding) {
            mainInfo.initView(offerItem)
            offerName.text = offerItem.name
            Log.e("OFFER", offerItem.toString())
            detailContainer.background = GradientDrawable(
                firstColor = offerItem.backgroundFirstColor,
                secondColor = offerItem.backgroundSecondColor,
            )
            headerView.initView(offerItem, controller::onFavoriteChecked)
            longDescription.text = offerItem.longDescription
            if (!offerItem.isActive) {
                mainInfo.alpha = 0.5F
                Snackbar.make(binding.root, R.string.inactive, Snackbar.LENGTH_LONG).show()
            }
        }

    }
}
