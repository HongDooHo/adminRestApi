<template>
    <div class="p-grid crud-demo">
        <div class="p-col-12">
            <div class="card">
                <Toast/>
                <Toolbar class="p-mb-4">
                    <template v-slot:left>
                        <Button label="New" icon="pi pi-plus" class="p-button-success p-mr-2" @click="openNew" />
                        <Button label="Export" icon="pi pi-upload" class="p-button-help" @click="exportCSV($event)" />
                    </template>
                </Toolbar>

                <DataTable ref="dt" :value="admins" @refreshData="refreshList" class="p-datatable-customers" :rows="10" style="margin-bottom: 20px;" :paginator="true">
                    <Column field="id" header="ID" :sortable="true"></Column>
                    <Column field="admin_email" header="Admin" ></Column>
                    <Column field="user_name" header="UserName" ></Column>
                    <Column field="user_email" header="UserEmail" ></Column>
                    <Column field="donation_price" header="Price" ></Column>
                    <Column field="donation_place" header="Place" ></Column>
                    <Column field="donation_date" header="Date" ></Column>
<!--                    <Column field="created_at" header="CreatedDate" ></Column>-->
                    <Column field="created_by" header="CreatedBy" ></Column>
                </DataTable>

                <Dialog v-model:visible="productDialog" v-on:submit.prevent="submitForm" :style="{width: '450px'}" header="기부자 등록" :modal="true" class="p-fluid">
<!--					<form v-on:submit.prevent="submitForm">-->
						<img :src="'assets/layout/images/product/' + product.image" :alt="product.image" class="product-image" v-if="product.image" />
						<div class="p-field">
							<label for="name">Name</label>
							<InputText id="name" v-model.trim="form.user_name" required="true" />
<!--							 <small class="p-invalid" v-if="submitted && !product.name">Name is required.</small>-->
						</div>
                        <div class="p-field">
                            <label for="email">Eamil</label>
                            <InputText id="email" v-model.trim="form.user_email" required="true"/>
                        </div>
                        <div class="p-field">
                            <label for="donation_place">Place</label>
                            <Dropdown id="donation_place" v-model="form.donation_place"
                                      :options="dropdownPlaces" optionLabel="name"
                                      placeholder="Select Address"></Dropdown>
                        </div>
						<div class="p-formgrid p-grid">
							<div class="p-field p-col">
								<label for="price">Price</label>
								<InputNumber id="price" v-model="form.donation_price" integeronly/>
							</div>
							<div class="p-field p-col">
								<label for="donation_date">Donation DATE</label>
								<InputText id="donation_date" v-model="form.donation_date" placeholder="YYYY-MM-DD" />
							</div>`
						</div>
						<template #footer>
							<Button label="Cancel" icon="pi pi-times" class="p-button-text" @click="hideDialog"/>
							<Button type="submit" label="Save" icon="pi pi-check" class="p-button-text" @click="submitForm" />
						</template>
<!--					</form>-->
				</Dialog>


            </div>
        </div>
    </div>

    

</template>

<script>
import DonationService from '../service/DonationService';
import http from "../service/http-common";
// import axios from "axios";

export default {
    data() {
		return {
			form: {
				admin_email: 'admin@naver.com',
				user_name: '김정호',
				user_email: 'kjhkjh@gmail.com',
				donation_price: 300,
				donation_place: '서울시 구파발',
                donation_date: '2020-11-25 11:22:11',
			},
            dropdownPlaces: [
                {name: "서울시 강동구"},
                {name: "서울시 기흥구"},
                {name: "파주시 봉일천"},
                {name: "서울시 은평구"}
            ],
			admins: [],
            prices: [],
			products: null,
			productDialog: false,
			deleteProductDialog: false,
			deleteProductsDialog: false,
			product: {},
			selectedProducts: null,
			filters: {},
			submitted: false,
            donations: null,
        }
    },
    donationService: null,
    created() {
        this.donationService = new DonationService();
    },
    mounted() {
        this.donationService.getDonations().then(data => this.donations = data);
		this.retrieveAdmins();
    },
    methods: {
        openNew() {
			this.product = {};
			this.submitted = false;
			this.productDialog = true;
        },
        hideDialog() {
			this.productDialog = false;
			this.submitted = false;
        },
        saveProduct(){

        },
        exportCSV() {
			this.$refs.dt.exportCSV();
		},
		retrieveAdmins() {
			http
					.get("/admin/all")
					.then(response => {
						this.admins = response.data;
						// console.log(this.admins);
					})
					.catch(e => {
						console.log(e);
					});
		},
		refreshList() {
			this.retrieveAdmins();
		},
		submitForm() {
			http
				.post("/admin", this.form, header)
				.then((req) => {
					console.log(req);
					console.log("success")
				})
				.catch((err) => {
					console.log(err);
				})
                .finally(() => {
                    this.form.user_name = '';
                    this.form.user_email = '';
                    this.form.donation_place = '';
                    this.form.donation_price = null;
                    this.form.donation_date = null;

                    this.productDialog = false;
                })
			/*axios.post('/admin', {
                admin_email: 'admin@naver.com',
                user_name: '김정호',
                user_email: 'kjhkjh@gmail.com',
                donation_price: 300,
                donation_place: '서울시 구파발',
                donation_date: '2020-11-25 11:22:11',
            })
            .then(res => console.log(res))
            .finally(() => {
                this.form.user_name = '';
                this.form.user_email = '';
                this.form.donation_place = '';
                this.form.donation_price = null;
                this.form.donation_date = null;

                this.productDialog = false;
            })*/


		}
    }
}
</script>

<style>

</style>