<template>
  <div class="board-create-container">
    <div class="input-box">      
      <div class="close" @click="closeCompareModal">
        <font-awesome-icon :icon="['fas', 'circle-xmark']" style="color: #000000;" size="xl"/>
      </div>
  <div class="comparison-container">
    <div class="comparison-header">
      <h2>제품 비교하기</h2>
    </div>
    <table class="comparison-table">
      <tbody>
        <tr>
          <th></th>
          <td v-for="drink in basket" :key="`${drink.drinkId}`">
            <img :src="drink.drinkUrl" :alt="drink.drinkName" class="drink-image">
          </td>
        </tr>
        <tr>
          <th></th>
          <td v-for="drink in basket" :key="`${drink.drinkId}`" class="coffee-name">
            {{ drink.drinkName }}
          </td>
        </tr>
        
        
        <tr>
          <th>카페인</th>
          <td v-for="drink in basket" :key="`${drink.drinkId}`">
            {{ drink.drinkCaffeine || '0'}} mg
          </td>
        </tr>
        <tr>
          <th>당류</th>
          <td v-for="drink in basket" :key="`sugar-${drink.drinkId}`">
            {{ drink.drinkSugar || '0'}} g
          </td>
        </tr>
        <tr>
          <th>포화지방</th>
          <td v-for="drink in basket" :key="`sodium-${drink.drinkId}`">
            {{ drink.drinkFat || '0'}} g
          </td>
        </tr>
        <tr>
          <th>나트륨</th>
          <td v-for="drink in basket" :key="`sodium-${drink.drinkId}`">
            {{ drink.drinkNatrium || '0'}} mg
          </td>
        </tr>
        <tr>
          <th>칼로리</th>
          <td v-for="drink in basket" :key="`calories-${drink.drinkId}`">
            {{ drink.drinkCalorie || '0'}} kcal
          </td>
        </tr>
        <tr>
          <th>용량</th>
          <td v-for="drink in basket" :key="`volume-${drink.drinkId}`">
            {{ drink.drinkMl === 0 ? drink.drinkOz + ' oz' : (drink.drinkMl ? drink.drinkMl + ' ml' : '-') }}
          </td>
        </tr>        
        <tr>
          <th>단백질</th>
          <td v-for="drink in basket" :key="`protein-${drink.id}`">
            {{ drink.drinkProtein || '0'}} g
          </td>
        </tr>
        <tr>
          <th>알레르기</th>
          <td v-for="drink in basket" :key="`allergy-${drink.idrinkId}`">
            {{ drink.drinkAllergy || '-'}}
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</div>
</div>
</template>

<script setup>
import { useComparisonStore } from '../../stores/comparison';
import { isCompareModal } from '../../stores/util'

const closeCompareModal = () => {
  isCompareModal.value = false
  
}
const comparisonStore = useComparisonStore();
const basket = comparisonStore.basket;

</script>

<style>

.board-create-container {
  position: fixed;
  width: 100vw;
  height: 100vh;
  background-color: rgba(128, 128, 128, 0.863) !important;
  top: 0;
  left: 0;
  margin: 0;
  z-index: 99 !important;
  display: flex;
  justify-content: center;
  align-items: center;
}

.input-box {
  background: #ffffff;
  border-radius: 30px;
  border-style: solid;
  border-color: #d9d9d9;
  border-width: 1px;
  position: absolute;
  width: 750px;
  height: 580px;
  box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
  top: 40px;
}

.close {
  display: flex;
  position: absolute;
  top: 10px;
  right: 10px;
  cursor: pointer;
}
.comparison-container {
  text-align: center;
}

.comparison-header h2 {
  margin-top: 5%;
  margin-bottom: 10px;
  padding-bottom: 0.5em;
  color: #562B1A;
  border-bottom: 2px solid #562B1A;
  display: inline-block;
}

.comparison-images {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.comparison-item {
  text-align: center;
  margin: 0 10px;
}

.drink-image {
  max-width: 100px;
  height: 100px;
}

.coffee-name {
  margin-top: 5px;
  color: #562B1A; 
  font-size: 15px;
  font-weight: bolder;  
}

.comparison-table {
  margin-top: 20px;
  border-collapse: collapse;
  width: 95%; 
  margin: auto;
  border-bottom: 3px solid #562B1A;
}
.comparison-table th {
  background-color: #ddd; 
  color: black;
  border-right: 1px solid #ddd;
  height: 33px;
}

.comparison-table td {
  color: #000; 
  border-bottom: 1px solid #ddd;
  /* height: 20px; */
}


/* 세로 줄 색상을 회색으로 설정 */
.comparison-table th, .comparison-table td {
  border-left: 1px solid #ddd;
}


/* 첫 번째 행의 배경색 제거 */
.comparison-table tr:first-child th {
  background-color: transparent; /* 배경색을 투명하게 설정하여 제거 */
}


/* 첫 번째 열의 세로줄 제거 */
.comparison-table tr:first-child th, 
.comparison-table tr:first-child td {
  border-left: none;
  border-right: none;
}

/* 두 번째 행부터 세로줄 시작 */
.comparison-table tr:nth-child(3) th, 
.comparison-table tr:nth-child(3) td {
  border-left: 1px solid #ddd;
}

/* 마지막 세로줄 제거 */
.comparison-table td:last-child {
  border-right: none;
}


/* 두 번째 행의 배경색을 흰색으로 변경 */
.comparison-table tr:nth-child(2) th,
.comparison-table tr:nth-child(2) td {
  background-color: #FFF; 
  border-right: none;
  border-left: none;
}

.comparison-table tr:nth-child(1) th,
.comparison-table tr:nth-child(1) td {
  border-top: none;
  border-bottom: none
}
.comparison-table tr:nth-child(2) th,
.comparison-table tr:nth-child(2) td {
  border-top: none;
  border-bottom: 3px solid #000;
}


</style>

