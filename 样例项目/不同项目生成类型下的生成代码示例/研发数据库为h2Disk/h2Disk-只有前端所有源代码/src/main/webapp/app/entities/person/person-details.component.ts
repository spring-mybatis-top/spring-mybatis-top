import { Component, Vue, Inject } from 'vue-property-decorator';

import { IPerson } from '@/shared/model/person.model';
import PersonService from './person.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class PersonDetails extends Vue {
  @Inject('personService') private personService: () => PersonService;
  @Inject('alertService') private alertService: () => AlertService;

  public person: IPerson = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.personId) {
        vm.retrievePerson(to.params.personId);
      }
    });
  }

  public retrievePerson(personId) {
    this.personService()
      .find(personId)
      .then(res => {
        this.person = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
