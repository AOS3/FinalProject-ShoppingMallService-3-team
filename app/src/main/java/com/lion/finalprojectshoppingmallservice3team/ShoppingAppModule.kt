package com.lion.finalprojectshoppingmallservice3team

import com.lion.finalprojectshoppingmallservice3team.creator.data.repository.CreatorRepository
import com.lion.finalprojectshoppingmallservice3team.creator.data.service.CreatorService
import com.lion.finalprojectshoppingmallservice3team.customer.data.repository.CustomerRepository
import com.lion.finalprojectshoppingmallservice3team.customer.data.service.CustomerService
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
    fun customerServiceProvider(customerRepository: CustomerRepository) : CustomerService{
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
}