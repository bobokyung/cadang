<template>
  <div class="main-container">
    <div class="user-info">
      <div class="user-set">
        <h2>{{ userStore.getUserName }} 님</h2>
        <font-awesome-icon :icon="['fas', 'comments']" style="color: #000000;" size="2xl" @click="goChat" class="chat"/>
      </div>
      <div @click="goSugar">
        <img src="@/components/icons/ca_toggle.png" alt="당 섭취량 메인페이지로 이동" class="toggle">
      </div>
    </div>

    <div>
      <p>카페인 섭취량</p>
      <div class="info-box">

        <div v-if="accumulateStore.getAccumulateToday.accumulateCaffeine < userStore.getUserRDI.userCaffeine * 1 / 2">
          <img src="@/components/icons/ca_good.png" alt="양호 이미지" class="status-img left-info">
        </div>
        <div v-else-if="accumulateStore.getAccumulateToday.accumulateCaffeine < userStore.getUserRDI.userCaffeine">
          <img src="@/components/icons/ca_soso.png" alt="보통 이미지" class="status-img left-info">
        </div>
        <div v-else>
          <img src="@/components/icons/ca_bad.png" alt="나쁨 이미지" class="status-img left-info">
        </div>

        <div class="mid-info">
          <p class="recent-drink">방금 마신 음료</p>
          <div v-if="recordsStore.getDayDrink.length > 0" class="drink-info">
            {{ (recordsStore.getDayDrink[recordsStore.getDayDrink.length - 1].drinkCaffeine
              + 75 * recordsStore.getDayDrink[recordsStore.getDayDrink.length - 1].plusShot).toFixed(1) }}mg
          </div>
          <div v-else class="nothing-info">
            오늘 마신 음료가 없습니다!
          </div>

          <p class="today-title">하루 총합 섭취량 / 권장량</p>
            <p v-if="accumulateStore.getAccumulateToday.accumulateCaffeine < userStore.getUserRDI.userCaffeine / 2.0" class="today-info font_green">
              {{ accumulateStore.getAccumulateToday.accumulateCaffeine }} / 
              {{ userStore.getUserRDICaffeine }}mg
            </p>
            <p v-else class="today-info font_red">
              {{ accumulateStore.getAccumulateToday.accumulateCaffeine }} / 
              {{ userStore.getUserRDICaffeine }}mg
            </p>
        </div>

        <div class="right-info big-font">
          <div v-if="accumulateStore.getAccumulateToday.accumulateCaffeine < userStore.getUserRDI.userCaffeine * 1 / 2">
            <p>건강도장 쾅!</p>
          </div>
          <div v-else-if="accumulateStore.getAccumulateToday.accumulateCaffeine < userStore.getUserRDI.userCaffeine">
            <p>깊은 잠이 그립지 않으신가요?</p>
          </div>
          <div v-else="accumulateStore.getAccumulateToday.accumulateCaffeine >= userStore.getUserRDI.userCaffeine">
            <p>혈관에 커피가 흐르진 않나요?</p>
          </div>
        </div>
      </div>
    </div>

    <div v-if="getCnt > 0">
      <ChartCaffeine />
    </div>

    <div v-else>
      <p>최근에 마신 카페인을 한눈에 보아요</p>
      <div class="info-box">
        <p class="chart-text-box">
          최근 3개월 간 음료를 기록하지 않았거나<br>
          카페인이 들어간 음료를 마시지 않았습니다.
        </p>
      </div>
    </div>

    <div v-if="recommendStore.getRecentDrinkName">
      <p>{{ recommendStore.getRecentDrinkName.drinkName }}와/과 비슷한 이 음료는 어때요?</p>
      <div class="recommend-box">
        <div class="recommend-info-left">
          <img :src="recommendOne.drinkUrl" alt="Recommended Drink" class="photo" />
          <button @click="goRecommedModal(recommendOne)" class="go-button">
            <font-awesome-icon :icon="['fas', 'magnifying-glass']" style="color: #000000;"/>
          </button>
          <p>{{ recommendOne.cafeName }}<br>
          {{ recommendOne.drinkName }}</p>
        </div>
        <div class="recommend-info-right">
          <img :src="recommendTwo.drinkUrl" alt="Recommended Drink" class="photo" />
          <button @click="goRecommedModal(recommendTwo)" class="go-button">
            <font-awesome-icon :icon="['fas', 'magnifying-glass']" style="color: #000000;"/>
          </button>
          <p>{{ recommendTwo.cafeName }}<br>
          {{ recommendTwo.drinkName }}</p>
        </div>
      </div>
    </div>

    <div v-else>
      <p>카페인 함량이 적은 이 음료는 어때요?</p>
      <div class="recommend-box">
        <div class="recommend-info-left">
          <img :src="recommendOne.drinkUrl" alt="Recommended Drink" class="photo" />
          <button @click="goRecommedModal(recommendOne)" class="go-button">
            <font-awesome-icon :icon="['fas', 'magnifying-glass']" style="color: #000000;"/>
          </button>
          <p>{{ recommendOne.cafeName }}<br>
          {{ recommendOne.drinkName }}</p>
        </div>
        <div class="recommend-info-right">
          <img :src="recommendTwo.drinkUrl" alt="Recommended Drink" class="photo" />
          <button @click="goRecommedModal(recommendTwo)" class="go-button">
            <font-awesome-icon :icon="['fas', 'magnifying-glass']" style="color: #000000;"/>
          </button>
          <p>{{ recommendTwo.cafeName }}<br>
          {{ recommendTwo.drinkName }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import ChartCaffeine from '@/components/main/ChartCaffeine.vue';

import { computed, ref, watch } from 'vue';
import { onMounted } from 'vue';
import router from '@/router';

import { useUserStore } from '@/stores/user';
import { useAccumulateStore } from '@/stores/accumulate';
import { useRecordsStore } from '@/stores/records';
import { useRecommendStore } from '@/stores/recommend';

import { isRecommedModal, recommedDrinkInfo, isInputModal, isInputNothingModal } from '../../stores/util'

const userStore = useUserStore()
const accumulateStore = useAccumulateStore()
const recordsStore = useRecordsStore()
const recommendStore = useRecommendStore()

const recommendOne = ref({})
const recommendTwo = ref({})

let cnt = ref(0)
const getCnt = computed(() => {
  return cnt.value
})

watch(isInputModal, (newValue, oldValue) => {
  if (!newValue ) {
    if(isInputNothingModal.value)
      return;

    router.go();
  }
});

watch(isInputNothingModal, (newValue, oldValue) => {
  if (!newValue ) {
    if(isInputModal.value)
      return;

    router.go();
  }
});


// 데이터를 가져오기 위한 함수
onMounted(async () => {

  const urlParams = new URLSearchParams(window.location.search);
  // 'code' 파라미터 값 가져오기
  const kakaoCode = urlParams.get('code');

  if (kakaoCode != null && kakaoCode.length > 0) {
    await userStore.sendKakaoToken(kakaoCode);

    // URL에서 'code' 파라미터 제거
    const newUrl = window.location.origin + window.location.pathname;
    history.replaceState({}, document.title, newUrl);
  }

  // 현재 날짜를 알기 위한 변수
  const todayDate = new Date()
  const year = todayDate.getFullYear()
  let month = todayDate.getMonth() + 1
  let day = todayDate.getDate()

  month = month < 10 ? '0' + month.toString() : month.toString()
  day = day < 10 ? '0' + day.toString() : day.toString()

  const date = ref(null)
  date.value = year + month + day

  await userStore.researchName()                        // 닉네임
  await userStore.researchAmount()                      // 권장량
  await accumulateStore.today()                         // 섭취량
  await recommendStore.researchRecommendCaffeine()     // 기록 기반 음료추천 카페인
  await recordsStore.researchDayDrink(date)           // 방금 마신 음료 계산을 위한 일자별 기록
  await accumulateStore.duration()                    // 차트 표시 여부 결정
  await recommendStore.researchRecentDrinkName()      // 기록 기반 최근 마신 음료 이름

  watch(() => accumulateStore.getAccumulateList, (newData) => {
    if (newData.length > 0) {
      newData.forEach(data => {
        cnt.value += data.accumulateCaffeine
      })
    }
  })

  // 추천 음료 2개 가지고 옴
  watch(() => recommendStore.getRecommendedCaffeine, (recommend) => {
    if (recommend.length > 0) {
      recommendOne.value = recommend[0]
      recommendTwo.value = recommend[1]
    }
  })
  
})

// 당 섭취량 메인페이지로 이동
const goSugar = () => {
  router.push({ name: 'mainSugar' })
}

// 추천 음료 상세페이지로 이동
const goRecommedModal = (recommendDrinkInfo) => {
  recommedDrinkInfo.value = recommendDrinkInfo

  if (recommedDrinkInfo.value) {
    isRecommedModal.value = true
  } else {
    alert('해당 음료를 찾을 수 없습니다.')
  }
}

// 채팅으로 이동
const goChat = () => {
  router.push({ name: 'chat' })
}
</script>

<style scoped>
@import "../../components/font/font.css";
@import "../../components/color/color.css";

h2 {
  color: #562B1A;
  font-weight: bold;
  text-align: left;
  margin-right: 20px;
}

p {
  font-weight: bold;
}

.main-container {
  position: relative;
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: center;
}

.toggle {
  width: 75px;
  cursor: pointer;
}

.user-info {
  display: flex;
  flex-direction: flex;
  justify-content: space-between;
  align-items: center;
  width: 700px;
}

.user-set {
  display: flex;
  align-items: center;
}

.info-box {
  background: #ffffff;
  border-radius: 30px;
  border-style: solid;
  border-color: #d9d9d9;
  border-width: 1px;
  display: flex;
  align-items: center;
  width: 700px;
  box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
  margin-bottom: 10px;
}

.recent-drink {
  font-size: 25px;
  margin-bottom: 0;
}

.drink-info {
  font-size: 30px;
  color: #562B1A;
  font-weight: bold;
}

.nothing-info {
  font-size: 20px;
  color: #562B1A;
  font-weight: bold;
}

.status-img {
  width: 130px;
  height: 130px;
  margin-left: 10px;
  margin-right: 20px;
}

.left-info {
  flex: 1;
}

.mid-info {
  flex: 3;
}

.right-info {
  flex: 3;
  margin-right: 30px;
}

.today-title {
  margin-bottom: 0;
}

.today-info {
  margin-top: 0;
}

.chart-text-box {
  width: 500px;
  height: 250px;
  margin: auto;
  font-size: 20px;
  font-weight: bold;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
}

.recommend-box {
  display: flex;
  justify-content: center;
}

.recommend-info-left {
  position: relative;
  background: #ffffff;
  border-radius: 30px;
  border-style: solid;
  border-color: #d9d9d9;
  border-width: 1px;
  text-align: center;
  width: 330px;
  box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
  margin-bottom: 10px;
  margin-right: 20px;
}

.recommend-info-right {
  position: relative;
  background: #ffffff;
  border-radius: 30px;
  border-style: solid;
  border-color: #d9d9d9;
  border-width: 1px;
  text-align: center;
  width: 330px;
  box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
  margin-bottom: 10px;
  margin-left: 20px;
}

.photo {
  position: relative;
  height: 150px;
  margin: 10px;
  border-radius: 20px;
}

.go-button {
  position: absolute;
  top: 30px;
  right: 70px;
  border: none;
  background-color: transparent;
  transform: translate(50%, -50%);
  cursor: pointer;
  font-size: 18px;
}

.chat {
  cursor: pointer;
}
</style>