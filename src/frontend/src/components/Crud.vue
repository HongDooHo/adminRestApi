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
                    <Column field="created_at" header="CreatedDate" ></Column>
                    <Column field="created_by" header="CreatedBy" ></Column>
                </DataTable>

                <Dialog v-model:visible="productDialog" :style="{width: '450px'}" header="기부자 등록" :modal="true" class="p-fluid">
					<img :src="'assets/layout/images/product/' + product.image" :alt="product.image" class="product-image" v-if="product.image" />
					<div class="p-field">
						<label for="name">Name</label>
						<InputText id="name" v-model.trim="product.name" required="true" autofocus :class="{'p-invalid': submitted && !product.name}" />
						<!-- <small class="p-invalid" v-if="submitted && !product.name">Name is required.</small> -->
					</div>
					<div class="p-field">
						<label for="description">Description</label>
						<Textarea id="description" v-model="product.description" required="true" rows="3" cols="20" />
					</div>

					<div class="p-field">
						<label class="p-mb-3">Category</label>
						<div class="p-formgrid p-grid">
							<div class="p-field-radiobutton p-col-6">
								<RadioButton id="category1" name="category" value="Accessories" v-model="product.category" />
								<label for="category1">Accessories</label>
							</div>
							<div class="p-field-radiobutton p-col-6">
								<RadioButton id="category2" name="category" value="Clothing" v-model="product.category" />
								<label for="category2">Clothing</label>
							</div>
							<div class="p-field-radiobutton p-col-6">
								<RadioButton id="category3" name="category" value="Electronics" v-model="product.category" />
								<label for="category3">Electronics</label>
							</div>
							<div class="p-field-radiobutton p-col-6">
								<RadioButton id="category4" name="category" value="Fitness" v-model="product.category" />
								<label for="category4">Fitness</label>
							</div>
						</div>
					</div>

					<div class="p-formgrid p-grid">
						<div class="p-field p-col">
							<label for="price">Price</label>
							<InputNumber id="price" v-model="product.price" mode="currency" currency="USD" locale="en-US" />
						</div>
						<div class="p-field p-col">
							<label for="quantity">Quantity</label>
							<InputNumber id="quantity" v-model="product.quantity" integeronly />
						</div>
					</div>
					<template #footer>
						<Button label="Cancel" icon="pi pi-times" class="p-button-text" @click="hideDialog"/>
						<Button label="Save" icon="pi pi-check" class="p-button-text" @click="saveProduct" />
					</template>
				</Dialog>


            </div>
        </div>
    </div>

    

</template>

<script>
import DonationService from '../service/DonationService';
import http from "../service/http-common";

export default {
    data() {
		return {
			admins: [],
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
						console.log(this.admins);
					})
					.catch(e => {
						console.log(e);
					});
		},
		refreshList() {
			this.retrieveAdmins();
		}
    }
}
</script>

<style>

</style>