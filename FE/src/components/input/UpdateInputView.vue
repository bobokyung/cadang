<template>
  <div class="board-create-container">
    <div class="update-box">
      <div class="close" @click="closeUpdateModal">
        <font-awesome-icon :icon="['fas', 'circle-xmark']" style="color: #000000;" size="xl"/>
      </div>

      <div class="update-container">
        <div>
          <h2>카페에서 마신 음료 기록을 수정해보세요</h2>
        </div>

        <div>
          <label for="cafeSelect" class="big-font">카페명</label>
          <select name="cafeSelect" id="cafeSelect"
          v-model="cafeId" @change="changeCafeDrinkList" class="button_select select">
            <option v-for="cafe in drinkStore.getCafeList"
            :key="cafe.cafeId"
            :value="cafe.cafeId">
              {{ cafe.cafeName }}
            </option>
          </select>
        </div>

        <div>
          <label for="drinkSelect" class="big-font">음료명</label>
          <input type="text" v-model="searchText"
          class="search-text-box" placeholder="음료명을 검색해주세요">
          <select name="drinkSelect" id="drinkSelect" v-model="drinkInfo"
          @change="changeDrinkInfo" class="button_select select">

          <option v-if="searchText===tempRecord.drinkName || !searchText" key="if-branch"
          v-for="drink in drinkStore.getCafeDrinkList"
          :key="drink.drinkId" :value="drink">
            {{ drink.drinkName }}
          </option>

          <option v-else key="else-branch"
          v-for="drink in filteredDrinks"
          :key="drink.drinkId" :value="drink">
            {{ drink.drinkName }}
          </option>
        </select>
        </div>
        
        <div class="item-container">
          <label for="plusShot" class="big-font">샷</label>
          <p class="mid-font">1샷 - 카페인 75mg</p>
          <button :disabled="!getminusCaffeineButton" @click="minusCaffeine" class="pm-button">
            <font-awesome-icon :icon="['fas', 'minus']" style="color: #000000;" />
          </button>
          {{ plusShot }}
          <button :disabled="!getplusCaffeineButton" @click="plusCaffeine" class="pm-button">
            <font-awesome-icon :icon="['fas', 'plus']" style="color: #000000;" />
          </button>
        </div>

        <div class="item-container">
          <label for="plusSyrup" class="big-font">시럽</label>
          <p class="mid-font">1시럽 - 당 6g</p>
          <button :disabled="!getminusSugarButton" @click="minusSugar" class="pm-button">
            <font-awesome-icon :icon="['fas', 'minus']" style="color: #000000;" />
          </button>
          {{ plusSyrup }}
          <button :disabled="!getplusSugarButton" @click="plusSugar" class="pm-button">
            <font-awesome-icon :icon="['fas', 'plus']" style="color: #000000;" />
          </button>
        </div>

        <div class="item-container">
          <button @click="drinkUpdateSubmit" class="button_caffeine buttons">수정완료</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed, ref, watch } from 'vue';
import { onMounted } from 'vue';

import { useDrinksStore } from "@/stores/drinks";
import { useRecordsStore } from "@/stores/records"
import { isUpdateInputModal, tempRecord } from '@/stores/util';

const drinkStore = useDrinksStore()
const recordsStore = useRecordsStore()

// 데이터를 가져오기 위한 함수
onMounted(() => {
  drinkStore.researchCafe()
})


// 음료 업데이트를 위해 보내줄 데이터
// 초기값은 캘린더에서 받아오는 값으로 설정
const recordId = tempRecord.value.recordId
const cafeName = ref(tempRecord.value.cafeName)
const drinkName = ref(tempRecord.value.drinkName)
const plusShot = ref(tempRecord.value.plusShot)
const plusSyrup = ref(tempRecord.value.plusSyrup)

// 카페 id, 음료 id, 각 음료의 카페인 함량, 당 함량
// 초기값은 캘린더에서 받아오는 값으로 설정
const cafeId = ref(0)
watch(() => drinkStore.getCafeList, (cafeList) => {
  if (cafeList.length > 0) {
    let findCafeId = cafeList.find(cafeList => cafeList.cafeName === tempRecord.value.cafeName)

    cafeId.value = findCafeId.cafeId
    drinkStore.researchCafeDrinks(cafeId.value)
  }
})

// 음료 전체 정보
// 초기값은 캘린더에서 받아오는 값으로 설정
const drinkInfo = ref({})
watch(() => drinkStore.getCafeDrinkList, (drinkList) => {
  if (drinkList.length > 0) {
    let findDrinkId = drinkList.find(drinkList => drinkList.cafeName === tempRecord.value.cafeName)
    drinkInfo.value = findDrinkId

    // 샷, 시럽 버튼 활성화
    activeminusCaffeineButton()
    activminusSugarButton()
    activeplusCaffeineButton()
    activeplusSugarButton()
  }
})

// 음료 필터링
const searchText = ref(tempRecord.value.drinkName)
const filteredDrinks = ref()

const searchFilteredDrinks = () => {
  filteredDrinks.value = drinkStore.getCafeDrinkList.filter(drink => {
    return drink.drinkName.toLowerCase().includes(searchText.value.toLowerCase())
  })
}

watch(searchText, () => {
  if (searchText.value) {
    searchFilteredDrinks()
  }
})

const drinkId = ref(tempRecord.value.drinkId)
const drinkCaffeine = ref(tempRecord.value.drinkCaffeine)
const drinkSugar = ref(tempRecord.value.drinkSugar)

const minusCaffeineButton = ref(false)
const minusSugarButton = ref(false)
const plusCaffeineButton = ref(false)
const plusSugarButton = ref(false)

// 모달창을 닫기 위한 함수
const closeUpdateModal = () => {
  isUpdateInputModal.value = false
}

// 이전 선택값 초기화해주는 함수
const reset = () => {
  drinkInfo.value = null
  cafeName.value = null
  drinkName.value = null
  plusShot.value = 0
  plusSyrup.value = 0
  searchText.value = null

  drinkId.value = 0
  drinkCaffeine.value = 0
  drinkSugar.value = 0

  minusCaffeineButton.value = false
  minusSugarButton.value = false
  plusCaffeineButton.value = false
  plusSugarButton.value = false
}

// 카페를 선택하면 해당 카페 음료를 가져오기 위한 함수
const changeCafeDrinkList = () => {
  if (cafeId.value) {
    reset()
    drinkStore.researchCafeDrinks(cafeId.value)
  }
}

// 샷, 시럽 버튼 활성화를 위한 computed => getter
const getminusCaffeineButton = computed(() => {
  return minusCaffeineButton.value
})

const getminusSugarButton = computed(() => {
  return minusSugarButton.value
})

const getplusCaffeineButton = computed(() => {
  return plusCaffeineButton.value
})

const getplusSugarButton = computed(() => {
  return plusSugarButton.value
})

// 샷, 시럽 버튼 활성화를 위한 함수 => Action
const activeminusCaffeineButton = () => {
  if (cafeName.value && drinkName.value && drinkCaffeine.value + 75 * (plusShot.value - 1) >= 0) {
    return minusCaffeineButton.value = true
  } else {
    return minusCaffeineButton.value = false
  }
}

const activminusSugarButton = () => {
  if (cafeName.value && drinkName.value && drinkSugar.value + 6 * (plusSyrup.value - 1) >= 0) {
    return minusSugarButton.value = true
  } else {
    return minusSugarButton.value = false
  }
}

const activeplusCaffeineButton = () => {
  if (cafeName.value && drinkName.value) {
    return plusCaffeineButton.value = true
  } else {
    return plusCaffeineButton.value = false
  }
}

const activeplusSugarButton = () => {
  if (cafeName.value && drinkName.value) {
    return plusSugarButton.value = true
  } else {
    return plusSugarButton.value = false
  }
}

// 카페 음료를 선택하면 해당 카페 음료 정보를 가져오기 위한 함수
const changeDrinkInfo = () => {
  if (drinkInfo.value) {
    cafeName.value = drinkInfo.value.cafeName
    drinkId.value = drinkInfo.value.drinkId
    drinkName.value = drinkInfo.value.drinkName
    drinkCaffeine.value = drinkInfo.value.drinkCaffeine
    drinkSugar.value = drinkInfo.value.drinkSugar
    searchText.value = drinkInfo.value.drinkName

    // 기존 입력한 샷, 시럽 추가 초기화
    plusShot.value = 0
    plusSyrup.value = 0

    // 샷, 시럽 버튼 활성화
    activeminusCaffeineButton()
    activminusSugarButton()
    activeplusCaffeineButton()
    activeplusSugarButton()
  }
}

const minusCaffeine = () => {
  plusShot.value -= 1
  activeminusCaffeineButton()
  activminusSugarButton()
  activeplusCaffeineButton()
  activeplusSugarButton()
}
const plusCaffeine = () => {
  plusShot.value += 1
  activeminusCaffeineButton()
  activminusSugarButton()
  activeplusCaffeineButton()
  activeplusSugarButton()
}
const minusSugar = () => {
  plusSyrup.value -= 1
  activeminusCaffeineButton()
  activminusSugarButton()
  activeplusCaffeineButton()
  activeplusSugarButton()
}
const plusSugar = () => {
  plusSyrup.value += 1
  activeminusCaffeineButton()
  activminusSugarButton()
  activeplusCaffeineButton()
  activeplusSugarButton()
}

// 업데이트된 음료 데이터 전송
const drinkUpdateSubmit = () => {
  if (cafeName.value && drinkName.value) {

    // 음료 업데이트를 위해 보내줄 데이터
    const drink = {
      recordId: recordId,
      drinkId: drinkId.value,
      cafeName: cafeName.value,
      drinkName: drinkName.value,
      plusShot: plusShot.value,
      plusSyrup: plusSyrup.value,
    }

    // 데이터 전송 및 창 닫기
    recordsStore.updateCafeDrink(drink)
    alert('입력이 완료되었습니다.')
    closeUpdateModal()

    } else {
      alert('입력값이 올바르지 않습니다. 다시 확인해주세요.')
    }
  }
</script>

<style scoped>
@import "../../components/font/font.css";
@import "../../components/color/color.css";

h2 {
  color: #562B1A;
  font-weight: bold;
  text-align: center;
}

div {
  margin: 10px;
}

.board-create-container {
  position: fixed;
  width: 100vw;
  height: 100vh;
  /* max-width:614px;
  max-height: 584px; */
  background-color: rgba(128, 128, 128, 0.863) !important;
  top: 0;
  left: 0;
  margin: 0;
  z-index: 99 !important;
  display: flex;
  justify-content: center;
  align-items: center;
}

.update-box {
  background: #ffffff;
  border-radius: 30px;
  border-style: solid;
  border-color: #d9d9d9;
  border-width: 1px;
  position: absolute;
  width: 500px;
  height: 470px;
  box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
}

.update-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.item-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.close {
  display: flex;
  position: absolute;
  top: 10px;
  right: 10px;
  cursor: pointer;
}

.pm-button {
  border: none;
}

.big-font {
  margin-right: 10px;
  font-size: 20px;
}

.mid-font {
  font-size: 15px;
  width: 300px;
}

.select {
  border-radius: 22px;
  border: 2px solid #846046;
  padding: 10px 20px;
  width: 353px;
}

.search-text-box {
  border: none;
  outline: none;
  position: absolute;
  margin-top: 12px;
  margin-left: 25px;
  z-index: 1;
  width: 250px;
}

.button_input_color {
  margin-right: 5px;
  color: black;
  font-size: 20px;
  font-weight: bold;
}

.button_caffeine {
  margin: auto;
  color: white;
  font-size: 20px;
  font-weight: bold;
}
.buttons {
  width: 150px;
  height: 60px;
  border: none;
}
</style>