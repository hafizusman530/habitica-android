package com.habitrpg.android.habitica.data.implementation;

import com.habitrpg.android.habitica.data.ApiClient;
import com.habitrpg.android.habitica.data.InventoryRepository;
import com.habitrpg.android.habitica.data.local.InventoryLocalRepository;
import com.habitrpg.android.habitica.models.inventory.Item;
import com.habitrpg.android.habitica.models.inventory.ItemData;
import com.habitrpg.android.habitica.models.inventory.Mount;
import com.habitrpg.android.habitica.models.inventory.Pet;
import com.habitrpg.android.habitica.models.inventory.QuestContent;
import com.habitrpg.android.habitica.models.user.HabitRPGUser;

import java.util.List;

import rx.Observable;

public class InventoryRepositoryImpl extends ContentRepositoryImpl<InventoryLocalRepository> implements InventoryRepository {

    public InventoryRepositoryImpl(InventoryLocalRepository localRepository, ApiClient apiClient) {
        super(localRepository, apiClient);
    }

    @Override
    public Observable<QuestContent> getQuestContent(String key) {
        return localRepository.getQuestContent(key);
    }

    @Override
    public Observable<List<ItemData>> getItems(List<String> searchedKeys) {
        return localRepository.getItems(searchedKeys);
    }

    @Override
    public long getArmoireRemainingCount() {
        return localRepository.getArmoireRemainingCount();
    }

    @Override
    public Observable<List<ItemData>> getInventoryBuyableGear() {
        return apiClient.getInventoryBuyableGear();
    }

    @Override
    public Observable<List<ItemData>> getOwnedEquipment(String type) {
        return localRepository.getOwnedEquipment(type);
    }

    @Override
    public Observable<List<ItemData>> getOwnedEquipment() {
        return localRepository.getOwnedEquipment();
    }

    @Override
    public Observable<List<Item>> getOwnedItems(String itemType) {
        return localRepository.getOwnedItems(itemType);
    }

    @Override
    public Observable<ItemData> getEquipment(String key) {
        return localRepository.getEquipment(key);
    }

    @Override
    public Observable<ItemData> openMysteryItem(String key) {
        return apiClient.openMysteryItem().doOnNext(itemData -> {
            itemData.setOwned(true);
            localRepository.saveEquipment(itemData);
        });
    }

    @Override
    public void saveEquipment(ItemData itemData) {
        localRepository.saveEquipment(itemData);
    }

    @Override
    public Observable<List<Mount>> getMounts() {
        return localRepository.getMounts();
    }

    @Override
    public Observable<List<Mount>> getMounts(String type, String group) {
        return localRepository.getMounts(type, group);
    }

    @Override
    public Observable<List<Pet>> getPets() {
        return localRepository.getPets();
    }

    @Override
    public Observable<List<Pet>> getPets(String type, String group) {
        return localRepository.getPets(type, group);
    }

    @Override
    public void updateOwnedEquipment(HabitRPGUser user) {
        localRepository.updateOwnedEquipment(user);
    }

    @Override
    public void changeOwnedCount(String type, String key, int amountToAdd) {
        localRepository.changeOwnedCount(type, key, amountToAdd);
    }
}