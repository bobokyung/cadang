<template>
  <rankDetailView v-if="isRankModal" :drink="selectedrankDrink" />
  <div class="search-frame">    
    <searchTopView />
    
    <div class="full-list-link">
      <h4 @click="goToFullList"> < 전체보기 </h4>
    </div>

   
    <div class="rankings">
      <div class="shading">
        <h2 class="ranking-title search">많이 <span class="highlight">검색</span>한 순위</h2>
        <div class="ranking-rows">
          <div class="ranking-column rank-text">     
            <div v-for="(keyword, index) in firstFourKeywords" :key="`first-${index}`" class="ranking-item" @click="performSearch(keyword)">
              {{ index + 1 }}. {{ keyword }} 
            </div>
          </div>
          <div class="ranking-column rank-text">          
            <div v-for="(keyword, index) in nextFourKeywords" :key="`next-${index}`" class="ranking-item" @click="performSearch(keyword)">
              {{ index + 5 }}. {{ keyword }}
            </div>
          </div>
        </div>
      </div>
      
      <div class="shading">
        <h2 class="ranking-title search">많이 <span class="highlight">기록</span>한 음료 순위</h2>
        <div class="ranking-rows">
          <div class="ranking-column rank-text">     
            <div v-for="(drink, index) in firstFiveRecords" :key="`record-first-${index}`" class="ranking-item2">              
              {{ index + 1 }}. {{ drink.cafeName }} {{ drink.drinkName }}              
            </div>
          </div>
       
          <!-- <div class="ranking-column rank-text">          
            <div v-for="(drink, index) in nextThreeRecords" :key="`record-next-${index}`" class="ranking-item">
              {{ index + 4 }}. {{ drink.cafeName }} {{ drink.drinkName }}
            </div>
          </div> -->
        </div>
      </div>
  </div>
</div>
  
</template>


<script setup>
import { ref, computed,onMounted } from 'vue';
import { useSearchStore } from '../../stores/search';
import router from '@/router';
import '../../components/color/color.css';
import searchTopView from './SearchTopView.vue';
import rankDetailView from '@/views/search/drinkDetailView.vue'
import { isRankModal } from '../../stores/util'
const searchStore = useSearchStore();
const keywordRanking = computed(() => searchStore.getKeywordRanking);
const recordRanking = computed(() => searchStore.getRecordRanking);

const selectedrankDrink = ref({});

const performSearch = async (keyword) => {
  const trimmedKeyword = keyword.trim();
  if (trimmedKeyword) {
    const hasResults = await searchStore.researchKeywordRank(trimmedKeyword);
    if (hasResults) {      
      router.push({ name: 'searchDetail', params: { keyword: trimmedKeyword } });
    } else {
      alert('검색 결과가 없습니다.');
    }
  } else {   
    window.location.reload();
  }
};


const goToFullList = () => {
  router.push({ name: 'searchDetail' });
};


const firstFourKeywords = computed(() => {
  return keywordRanking.value.slice(0, 4);
});


const nextFourKeywords = computed(() => {
  return keywordRanking.value.slice(4, 8);
});


const firstFiveRecords = computed(() => {
  return recordRanking.value.slice(0, 3);
});

// const nextThreeRecords = computed(() => {
//   return recordRanking.value.slice(3, 6);
// });

onMounted(() => {
  searchStore.bringKeywordRanking();
  searchStore.bringRecordRanking();
});
</script>



<style>
.search-frame {
  display: flex;
  flex-direction: column;
  align-items: center;
  max-width: 600px;
  margin: auto;
  margin-top: 40px;
  background: #FFF;
  padding: 20px;
}

.full-list-link {
  align-self: flex-start;
  margin-bottom: 20px; 
  margin-left: 55px; 
  cursor: pointer; 
}
.highlight {
  color: #846046; 
}
.rankings {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 97%;
}

.ranking-title {
  color: #000; 
  text-align: left;
  margin-left: 10px;
}


.ranking-rows {
  display: flex;
  justify-content: space-between; 
}
.ranking-column {
  display: flex;
  flex-direction: column; 
  width: 100%;
  margin: auto;
}
.ranking-item {
  margin-bottom: 5px; 
  text-align: left; 
  cursor: pointer;
}
.ranking-item2 {
  margin-bottom: 5px; 
  text-align: left; 
}
.shading {
  display: flex; 
  flex-direction: column; 
  flex-wrap: wrap; 
  align-items: left; 
  justify-content: flex-start;
  border-radius: 45px; 
  border: 1px solid #EFEFEF;
  background: var(--Color, #FFF);
  box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
  padding: 15px; 
  margin-bottom: 20px; 
  width: 90%;  
}

.rank-text{
  font-size: 20px;
  margin-top: 15x;
  margin-left: 10px;
}

</style>

