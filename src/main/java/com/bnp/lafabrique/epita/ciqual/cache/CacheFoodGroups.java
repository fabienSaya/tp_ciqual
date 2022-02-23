package com.bnp.lafabrique.epita.ciqual.cache;

import com.bnp.lafabrique.epita.ciqual.domaine.FoodSubSubGroup;
import com.bnp.lafabrique.epita.ciqual.exception.GroupDefinitionException;

import java.util.HashMap;


public class CacheFoodGroups {

    //faudrait probablement utiliser un concurrentHashMap pour une appli web et le gérer comme il faut pour acces concurrent.
    //Et eventuellement charger une sorte de reload depuis la BDD si qqn fait des inserts en base direct
    private static HashMap<String, HashMap<String, HashMap<String, FoodSubSubGroup>>> cachedSubSubGroup = new HashMap<>();

    //key=group code, value = group id
    private static HashMap<String,Long>  cachedGroupIds=new HashMap<>();

    //key=groupcode + subgroup code, value = subgroup id
    private static HashMap<String,Long>  cachedSubGroupIds=new HashMap<>();


    /**
     * lookup in cache for Groups id (group,subgroup,subsubgroup) based on "code" property of each group level
     * Warning: Will update the ids of the given foodSubSubGroupToUpdate with what is found in the caches
     *
     * @param foodSubSubGroupToUpdate object group, for which we want to update the ids
     * @return true if the subsubgroup was already in cache, false otherwise
     */
    public static boolean updateGroupsIds(FoodSubSubGroup foodSubSubGroupToUpdate) {

        //Maybe I'll do a deep clone to avoid changing the object parameter and return the update copy instead but not needed right now.

        String foodSubSubGroupToLookupCode = foodSubSubGroupToUpdate.getCode();
        String foodSubGroupToLookupCode = foodSubSubGroupToUpdate.getSubGroup().getCode();
        String foodGroupToLookupCode = foodSubSubGroupToUpdate.getSubGroup().getGroup().getCode();


        //we start with the higher level group then move to lower level subsubsub...group.

        HashMap<String, HashMap<String, FoodSubSubGroup>> foodSubGroupMap = cachedSubSubGroup.get(foodGroupToLookupCode);
        if (foodSubGroupMap==null)
            //the group does not exist at all so we have to create every thing.
            return false;
        else {
            //we get the group id
            Long idGroup = cachedGroupIds.get(foodGroupToLookupCode);
            //we update the object with the correct id
            foodSubSubGroupToUpdate.getSubGroup().getGroup().setId(idGroup);
        }

        HashMap<String, FoodSubSubGroup> foodSubSubGroupMap = foodSubGroupMap.get(foodSubGroupToLookupCode);
        if (foodSubSubGroupMap==null) {
            //subgroup does not exist at all so we return false
            return false;
        } else {
            //we get the subgroup id
            Long idSubGroup = cachedSubGroupIds.get(foodGroupToLookupCode+foodSubGroupToLookupCode);
            //we update the subgroupid
            foodSubSubGroupToUpdate.getSubGroup().setId(idSubGroup);
        }

        //we look if the subsubgroup exist
        FoodSubSubGroup foodSubSubGroup = foodSubSubGroupMap.get(foodSubSubGroupToLookupCode);

        if (foodSubSubGroup==null) {
            //subsubgroup does not exist we return false
            return false;
        } else {
            //we get the subgroup id and update the object id
            foodSubSubGroupToUpdate.setId(foodSubSubGroup.getId());
            //we return true as we found the object in the cache
            return true;
        }
    }

    /**
     * will add the foodSubSubGroupToAdd (which contains the full group chaine with proper ids) to the cache
     * if there was already this subsubgroup in the cache we override it with the new value
     *
     * @param foodSubSubGroupToAdd  subsubgroup to add to the cache
     */
    public static void add(FoodSubSubGroup foodSubSubGroupToAdd) throws GroupDefinitionException {

        //we lookup for codes
        String foodSubSubGroupToLookupCode = foodSubSubGroupToAdd.getCode();

        if (foodSubSubGroupToAdd.getSubGroup()==null) throw new GroupDefinitionException("The provided subsubgroup "+foodSubSubGroupToAdd.getCode()+" does not have a subgroup assigned");
        String foodSubGroupToLookupCode = foodSubSubGroupToAdd.getSubGroup().getCode();

        if (foodSubSubGroupToAdd.getSubGroup().getGroup()==null) throw new GroupDefinitionException("The provided subgroup "+foodSubSubGroupToAdd.getSubGroup().getCode()+" does not have a group assigned");
        String foodGroupToLookupCode = foodSubSubGroupToAdd.getSubGroup().getGroup().getCode();

        //we check if the group exist
        HashMap<String, HashMap<String, FoodSubSubGroup>> foodSubGroupMap = cachedSubSubGroup.get(foodGroupToLookupCode);

        if (foodSubGroupMap==null) {
            //means the group does not existe
            //we create the new group entry and add it to the cach
            foodSubGroupMap = new HashMap<>();
            cachedSubSubGroup.put(foodGroupToLookupCode, foodSubGroupMap);
            //we also add its id to the group id cache
            cachedGroupIds.put(foodGroupToLookupCode, foodSubSubGroupToAdd.getSubGroup().getGroup().getId());
        }

        HashMap<String, FoodSubSubGroup> foodSubSubGroupMap = foodSubGroupMap.get(foodSubGroupToLookupCode);
        if (foodSubSubGroupMap==null) {
            //means the subgroup does not existe
            //we create the new subgroup entry
            foodSubSubGroupMap = new HashMap<>();
            foodSubGroupMap.put(foodSubGroupToLookupCode, foodSubSubGroupMap);

            //we also add its id to the subgroup id cache
            cachedSubGroupIds.put(foodGroupToLookupCode+foodSubGroupToLookupCode,foodSubSubGroupToAdd.getSubGroup().getId());
        }

        //we add the subsubgroup and override if was already in cache
        foodSubSubGroupMap.put(foodSubSubGroupToLookupCode, foodSubSubGroupToAdd);

    }
}
