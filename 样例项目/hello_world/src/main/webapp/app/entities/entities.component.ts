import { Component, Provide, Vue } from 'vue-property-decorator';

import UserService from '@/entities/user/user.service';
import HelloWorldService from './hello-world/hello-world.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

@Component
export default class Entities extends Vue {
  @Provide('userService') private userService = () => new UserService();
  @Provide('helloWorldService') private helloWorldService = () => new HelloWorldService();
  // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
}
