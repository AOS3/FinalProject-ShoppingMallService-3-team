package com.lion.finalprojectshoppingmallservice3team

import com.lion.finalprojectshoppingmallservice3team.creator.data.repository.CreatorRepository
import com.lion.finalprojectshoppingmallservice3team.creator.data.repository.ShopRepository
import com.lion.finalprojectshoppingmallservice3team.creator.data.service.CreatorService
import com.lion.finalprojectshoppingmallservice3team.creator.data.service.ShopService
import com.lion.finalprojectshoppingmallservice3team.customer.data.repository.CustomerRepository
import com.lion.finalprojectshoppingmallservice3team.customer.data.repository.InquiryRepository
import com.lion.finalprojectshoppingmallservice3team.customer.data.service.CustomerService
import com.lion.finalprojectshoppingmallservice3team.customer.data.service.InquiryService
import com.lion.finalprojectshoppingmallservice3team.customer.data.repository.ProductRepository
import com.lion.finalprojectshoppingmallservice3team.customer.data.service.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ShoppingAppModule {

    @Provides
    @Singleton
    fun customerRepositoryProvider() : CustomerRepository{
        return CustomerRepository()
    }

    @Provides
    @Singleton
    fun customerServiceProvider(customerRepository: CustomerRepository) : CustomerService {
        return CustomerService(customerRepository)
    }

    @Provides
    @Singleton
    fun creatorRepositoryProvider() : CreatorRepository{
        return CreatorRepository()
    }

    @Provides
    @Singleton
    fun creatorServiceProvider(creatorRepository: CreatorRepository) : CreatorService{
        return CreatorService(creatorRepository)
    }

    @Provides
    @Singleton
    fun shopRepositoryProvider() : ShopRepository {
        return ShopRepository()
    }

    @Provides
    @Singleton
    fun shopServiceProvider(shopRepository: ShopRepository) : ShopService{
        return ShopService(shopRepository)
    }

    @Provides
    @Singleton
    fun inquiryRepositoryProvider() : InquiryRepository {
        return InquiryRepository()
    }

    @Provides
    @Singleton
    fun inquiryServiceProvider(inquiryRepository: InquiryRepository) : InquiryService{
        return InquiryService(inquiryRepository)
    }


    @Provides
    @Singleton
    fun productRepositoryProvider() : ProductRepository {
        return ProductRepository()
    }

    @Provides
    @Singleton
    fun productServiceProvider(productRepository: ProductRepository) : ProductService {
        return ProductService(productRepository)
    }
}